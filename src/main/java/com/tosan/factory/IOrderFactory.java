package com.tosan.factory;


import com.tosan.entity.Order;
import com.tosan.model.OrderType;

import java.math.BigDecimal;

public interface IOrderFactory {
    Order createOrder(OrderType orderType, BigDecimal price, Integer quantity);
}
