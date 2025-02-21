package com.tosan.factory;

import com.tosan.annotations.InjectObject;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactoryInjector {
    private static final Map<Class<?>, Object> instanceCache = new HashMap<>();
    private static final Objenesis objenesis = new ObjenesisStd();

    public static <T> T createObject(Class<T> clazz) {
        if (instanceCache.containsKey(clazz)) {
            return (T) instanceCache.get(clazz);
        }
        try {
            T obj = objenesis.getInstantiatorOf(clazz).newInstance();
            injectDependencies(obj);
            instanceCache.put(clazz, obj);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Error creating object: " + clazz.getSimpleName(), e);
        }
    }

    private static void injectDependencies(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectObject.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(instance) == null) {
                        Object injectedObject = createObjectUsingReflection(field.getType());
                        field.set(instance, injectedObject);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error injecting dependency: " + field.getName(), e);
                }
            }
        }
    }

    private static Object createObjectUsingReflection(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating object using reflection: " + clazz.getSimpleName(), e);
        }
    }
}
