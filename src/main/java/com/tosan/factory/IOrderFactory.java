package com.tosan.factory;


import com.tosan.model.Order;
import com.tosan.model.OrderType;

import java.math.BigDecimal;

public interface IOrderFactory {
    Order createOrder(OrderType orderType, BigDecimal price, Integer quantity);
}
