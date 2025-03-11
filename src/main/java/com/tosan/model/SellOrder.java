package com.tosan.model;

import com.tosan.entity.Order;

import java.math.BigDecimal;
import java.util.UUID;

public class SellOrder extends Order {
    public SellOrder(BigDecimal price, Integer quantity) {
        super(price, quantity);
    }

    @Override
    public int compareTo(Order order) {
        return this.getPrice().compareTo(order.getPrice());
    }

    @Override
    protected String generateId() {
        return "s-" + UUID.randomUUID();
    }
}
