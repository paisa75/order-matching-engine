package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.OrderDto;

public interface OrderServicePort {
    OrderDto creatSellOrder(OrderDto OOrderDto, String username);

    OrderDto creatBuyOrder(OrderDto OOrderDto, String username);
}
