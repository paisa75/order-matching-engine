package com.tosan.annotations;

import com.tosan.validation.PositiveQuantityValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveQuantityValidator.class)
public @interface ValidQuantity {
    String message() default "Quantity must be a positive number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
