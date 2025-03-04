package com.tosan.validation;

import com.tosan.annotations.ValidPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PositivePriceValidator implements ConstraintValidator<ValidPrice, String> {
    @Override
    public void initialize(ValidPrice constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            BigDecimal price = new BigDecimal(value.trim());
            return price.compareTo(BigDecimal.ZERO) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}