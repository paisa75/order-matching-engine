package com.tosan.data;

import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.SellOrder;

import java.util.PriorityQueue;

public class OrderBook {
    PriorityQueue<Order> buyOrders = new PriorityQueue<>();
    PriorityQueue<Order> sellOrders = new PriorityQueue<>();
    public void addOrder(Order order) {
        if (order instanceof BuyOrder) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    public BuyOrder getHighestBuyOrder() {
        return (BuyOrder) buyOrders.peek();
    }
    public SellOrder getLowestSellOrder() {
        return (SellOrder) sellOrders.peek();
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }

}
