package com.tosan.logging;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {
    private final Object target;

    public LogProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        if (!interfaceType.isInterface()) {
            throw new IllegalArgumentException("Proxy can only be created for interfaces!");
        }

        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new LogProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = method.invoke(target, args);
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            System.out.println("Logging method: " + method.getName());
            Logger.log(target.getClass().getSimpleName(), method.getName(), args, result, executionTime);
        }
        return result;
    }
}
