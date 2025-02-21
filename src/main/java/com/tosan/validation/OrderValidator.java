package com.tosan.validation;

import com.tosan.exceptions.OrderException;

import java.math.BigDecimal;

public class OrderValidator {
    public void validateInputFormat(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new OrderException("Input cannot be empty.");
        }

        String[] parts = input.split("#");
        if (parts.length != 3) {
            throw new OrderException("Error: Input should have exactly 3 parts separated by '#'.");
        }

        String orderType = parts[0].trim();
        if (!orderType.equals("buyOrder") && !orderType.equals("sellOrder")) {
            throw new OrderException("Invalid order type. It should be either 'buyOrder' or 'sellOrder'.");
        }

        String priceStr = parts[1].trim();
        try {
            BigDecimal price = new BigDecimal(priceStr);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new OrderException("Price must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new OrderException("Price must be a valid number.", e);
        }

        String quantityStr = parts[2].trim();
        try {
            Integer quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new OrderException("Quantity must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new OrderException("Quantity must be a valid integer.");
        }
    }
}
