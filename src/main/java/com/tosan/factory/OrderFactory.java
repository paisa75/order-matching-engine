package com.tosan.factory;

import com.tosan.annotations.Component;
import com.tosan.exceptions.OrderException;
import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.OrderType;
import com.tosan.model.SellOrder;

import java.math.BigDecimal;

@Component
public class OrderFactory implements IOrderFactory {
    @Override
    public Order createOrder(OrderType orderType, BigDecimal price, Integer quantity) {
        if (orderType == OrderType.BUY_ORDER) {
            return new BuyOrder(price, quantity);
        } else if (orderType == OrderType.SELL_ORDER) {
            return new SellOrder(price, quantity);
        } else {
            throw new OrderException("Invalid order type");
        }
    }
}
