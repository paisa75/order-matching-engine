package com.tosan.annotations;

import com.tosan.validation.PositivePriceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositivePriceValidator.class)
public @interface ValidPrice {
    String message() default "Price must be a positive number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
