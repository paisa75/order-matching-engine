package com.tosan.factory;

import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;

public class OrderFactory {
    public static Order createOrder(String orderType, BigDecimal price, Integer quantity) {
        if (orderType.equalsIgnoreCase("buyOrder")) {
            return new BuyOrder(price, quantity);
        } else if (orderType.equalsIgnoreCase("sellOrder")) {
            return new SellOrder(price, quantity);
        } else {
            throw new IllegalArgumentException("Invalid order type");
        }
    }
}
