package com.tosan.data;

import com.tosan.annotations.Component;
import com.tosan.model.BuyOrder;
import com.tosan.model.Order;
import com.tosan.model.SellOrder;

import java.util.PriorityQueue;

@Component
public class OrderBook implements IOrderBook {
    PriorityQueue<Order> buyOrders = new PriorityQueue<>();
    PriorityQueue<Order> sellOrders = new PriorityQueue<>();

    @Override
    public void addOrder(Order order) {
        if (order instanceof BuyOrder) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    @Override
    public BuyOrder getHighestBuyOrder() {
        return (BuyOrder) buyOrders.peek();
    }

    @Override
    public SellOrder getLowestSellOrder() {
        return (SellOrder) sellOrders.peek();
    }

    @Override
    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    @Override
    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }

}
