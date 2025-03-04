package com.tosan.model;

import java.math.BigDecimal;
import java.util.UUID;

public class BuyOrder extends Order {
    public BuyOrder(BigDecimal price, Integer quantity) {
        super(price, quantity);
    }

    @Override
    public int compareTo(Order order) {
        return order.getPrice().compareTo(this.getPrice());
    }

    @Override
    protected String generateId() {
        return "B-" + UUID.randomUUID();
    }
}
