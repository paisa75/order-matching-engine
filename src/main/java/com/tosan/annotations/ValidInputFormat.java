package com.tosan.annotations;

import com.tosan.validation.InputFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InputFormatValidator.class)
public @interface ValidInputFormat {
    String message() default "Input must have exactly 3 parts separated by '#'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
