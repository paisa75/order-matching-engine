package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.OrderDto;

public interface OrderServicePort {
public OrderDto creatSellOrder(OrderDto OOrderDto, String username);
public OrderDto creatBuyOrder(OrderDto OOrderDto, String username);
}
