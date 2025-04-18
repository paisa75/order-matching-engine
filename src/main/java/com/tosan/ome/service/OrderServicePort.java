package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.OrderDto;

public interface OrderServicePort {
    boolean cancelOrderByTrackingCode(String trackingCode);

    OrderDto creatSellOrder(OrderDto orderDto, String username);

    OrderDto creatBuyOrder(OrderDto orderDto, String username);
}
