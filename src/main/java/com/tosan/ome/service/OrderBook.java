package com.tosan.ome.service;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.SellOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderBook {
    private List<BuyOrder> buyOrders = new ArrayList<>();
    private List<SellOrder> sellOrders = new ArrayList<>();

    public BuyOrder getHighestBuyOrder() {
        return buyOrders.getFirst();
    }


    public SellOrder getLowestSellOrder() {
        return sellOrders.getFirst();
    }
}
