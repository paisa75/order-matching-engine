package com.tosan.container;

import com.tosan.annotations.Component;
import com.tosan.annotations.InjectObject;
import com.tosan.annotations.Loggable;
import com.tosan.logging.LogProxy;
import com.tosan.scanner.PackageScanner;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanContainer {
    private final Map<Class<?>, Object> beans = new HashMap<>();
    private final Objenesis objenesis = new ObjenesisStd();

    public void scanAndInstantiate(String packageName) {
        Set<Class<?>> classes = PackageScanner.findAnnotatedClass(packageName, Component.class);
        for (Class<?> clazz : classes) {
            Object instance = createInstance(clazz);
            injectDependencies(instance);
            Object proxyInstance = createProxyIfNeeded(instance, clazz);
            beans.put(clazz, proxyInstance);

            for (Class<?> iface : clazz.getInterfaces()) {
                beans.put(iface, proxyInstance);
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        Object bean = beans.get(clazz);
        if (bean == null) {
            throw new IllegalArgumentException("No bean found for class: " + clazz.getName());
        }
        return clazz.cast(bean);
    }

    private void injectDependencies(Object instance) {
        Class<?> clazz = instance.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectObject.class)) {
                field.setAccessible(true);
                try {
                    Class<?> dependencyClass = field.getType();
                    Object dependency = beans.get(dependencyClass);

                    if (dependency == null) {
                        dependency = createInstance(dependencyClass);
                        injectDependencies(dependency);
                    }

                    field.set(instance, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error injecting dependency: " + field.getName(), e);
                }
            }
        }
    }


    private Object createInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            return objenesis.newInstance(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error creating instance for class: " + clazz.getName(), e);
        }
    }

    private Object createProxyIfNeeded(Object instance, Class<?> clazz) {
        if (hasLoggableMethods(clazz)) {
            Object proxyInstance = instance;

            if (clazz.getInterfaces().length > 0) {
                proxyInstance = Proxy.newProxyInstance(
                        clazz.getClassLoader(),
                        clazz.getInterfaces(),
                        new LogProxy(instance)
                );
            }
            return proxyInstance;
        }
        return instance;
    }

    private boolean hasLoggableMethods(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Loggable.class)) {
                return true;
            }
        }
        return false;
    }
}
