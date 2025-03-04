package com.tosan.validation;

import com.tosan.annotations.ValidOrderType;
import com.tosan.model.OrderType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderTypeValidator implements ConstraintValidator<ValidOrderType, String> {
    @Override
    public void initialize(ValidOrderType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals(OrderType.BUY_ORDER.label) || value.equals(OrderType.SELL_ORDER.label));
    }
}