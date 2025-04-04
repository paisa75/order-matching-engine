package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.OrderDto;
import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.SellOrder;
import com.tosan.ome.repository.entity.User;
import com.tosan.ome.repository.repositories.BuyOrderRepository;
import com.tosan.ome.repository.repositories.SellOrderRepository;
import com.tosan.ome.repository.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService implements OrderServicePort {

    private final SellOrderRepository sellOrderRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final UserRepository userRepository;
    private final MatchingEngine matchingEngine;
    @Override
    public OrderDto creatSellOrder(OrderDto orderDto, String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        SellOrder sellOrder = new SellOrder();
        BeanUtils.copyProperties(orderDto, sellOrder);
        if (byUsername.isEmpty()) {
            //TODO: custom exception: InvalidUserException
            throw new RuntimeException("user not found");
        }
        sellOrder.setUser(byUsername.get());
        sellOrder.setActive(true);
        SellOrder savedOrder = sellOrderRepository.save(sellOrder);
        orderDto.setId(String.valueOf(savedOrder.getId()));

        matchingEngine.matchOrders();
        return orderDto;
    }

    @Override
    public OrderDto creatBuyOrder(OrderDto orderDto, String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        BuyOrder buyOrder = new BuyOrder();
        BeanUtils.copyProperties(orderDto, buyOrder);
        if (byUsername.isEmpty()) {
            //TODO: custom exception
            throw new RuntimeException("user not found");
        }
        buyOrder.setUser(byUsername.get());
        buyOrder.setActive(true);
        BuyOrder savedOrder = buyOrderRepository.save(buyOrder);
        orderDto.setId(String.valueOf(savedOrder.getId()));
        matchingEngine.matchOrders();
        return orderDto;
    }
}
