package com.tosan.validation;

import com.tosan.annotations.ValidQuantity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveQuantityValidator implements ConstraintValidator<ValidQuantity, String> {
    @Override
    public void initialize(ValidQuantity constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            int quantity = Integer.parseInt(value.trim());
            return quantity > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}