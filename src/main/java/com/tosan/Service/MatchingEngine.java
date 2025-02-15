package com.tosan.Service;

import com.tosan.data.OrderBook;
import com.tosan.model.Order;

public class MatchingEngine {
    private final OrderBook orderBook;
    public MatchingEngine() {
        this.orderBook = new OrderBook();
    }

    public void processNewOrder(Order order) {
        orderBook.addOrder(order);
        matchOrders();
    }
    private void matchOrders() {}
}
