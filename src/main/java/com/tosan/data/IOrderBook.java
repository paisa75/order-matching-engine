package com.tosan.data;

import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.SellOrder;

import java.util.PriorityQueue;

public interface IOrderBook {
    void addOrder(Order order);

    BuyOrder getHighestBuyOrder();

    SellOrder getLowestSellOrder();

    PriorityQueue<Order> getBuyOrders();

    PriorityQueue<Order> getSellOrders();
}
