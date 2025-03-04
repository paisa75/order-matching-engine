package com.tosan.validation;

import com.tosan.exceptions.OrderException;
import com.tosan.model.InputOrder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class OrderValidator {
    private final Validator validator;

    public OrderValidator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validateInputFormat(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new OrderException("Input cannot be empty.");
        }

        String[] parts = input.split("#");
        if (parts.length != 3) {
            throw new OrderException("Input must have exactly 3 parts separated by '#'.");
        }

        String orderType = parts[0].trim();
        String orderPrice = parts[1].trim();
        String orderQuantity = parts[2].trim();

        InputOrder inputOrder = new InputOrder(orderType, orderPrice, orderQuantity);

        Set<ConstraintViolation<InputOrder>> violations = validator.validate(inputOrder);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<InputOrder> violation : violations) {
                errors.append(violation.getMessage()).append("\n");
            }
            throw new OrderException(errors.toString());
        }
    }
}
