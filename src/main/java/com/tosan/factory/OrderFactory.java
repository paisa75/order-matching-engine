package com.tosan.factory;

import com.tosan.annotations.Component;
import com.tosan.exceptions.OrderProcessingException;
import com.tosan.model.BuyOrder;
import com.tosan.entity.Order;
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
            throw new OrderProcessingException("Invalid order type");
        }
    }
}
