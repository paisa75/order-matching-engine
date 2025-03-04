package com.tosan.service;

import com.tosan.model.Order;
import com.tosan.model.OrderResult;

import java.util.List;

public interface IMatchingEngine {
    List<OrderResult> processNewOrder(Order order);
}
