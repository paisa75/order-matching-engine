package com.tosan.validation;

import com.tosan.annotations.ValidInputFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class InputFormatValidator implements ConstraintValidator<ValidInputFormat, String> {
    @Override
    public void initialize(ValidInputFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        String[] parts = value.split("#");
        return parts.length == 3;
    }
}
