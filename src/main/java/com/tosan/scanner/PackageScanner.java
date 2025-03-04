package com.tosan.scanner;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class PackageScanner {

    public static Set<Class<?>> findAnnotatedClass(String packageName, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(annotation);
    }

}
