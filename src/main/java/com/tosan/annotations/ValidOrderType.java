package com.tosan.annotations;


import com.tosan.validation.OrderTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderTypeValidator.class)
public @interface ValidOrderType {
    String message() default "Invalid order type. It should be either 'buyOrder' or 'sellOrder'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
