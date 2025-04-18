package com.tosan.ome.service;

import com.tosan.ome.controller.dtos.OrderDto;
import com.tosan.ome.controller.exceptions.InvalidTrackingCodeException;
import com.tosan.ome.controller.exceptions.OrderCancellationNotAllowedException;
import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.OrderStatus;
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
    private final TrackingCodeGenerator trackingCodeGenerator;


    //TODO: java doc
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
        sellOrder.setTrackingCode(trackingCodeGenerator.generate("SO"));

        SellOrder savedOrder = sellOrderRepository.save(sellOrder);
        orderDto.setId(String.valueOf(savedOrder.getId()));
        orderDto.setTrackingCode(savedOrder.getTrackingCode());

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
        buyOrder.setTrackingCode(trackingCodeGenerator.generate("BO"));

        BuyOrder savedOrder = buyOrderRepository.save(buyOrder);
        orderDto.setId(String.valueOf(savedOrder.getId()));
        orderDto.setTrackingCode(savedOrder.getTrackingCode());

        matchingEngine.matchOrders();
        return orderDto;
    }

    @Override
    public boolean cancelOrderByTrackingCode(String trackingCode) {
        if (trackingCode.startsWith("B")) {
            Optional<BuyOrder> buyOrderOptional = buyOrderRepository.findByTrackingCode(trackingCode);
            if (buyOrderOptional.isEmpty()) {
                throw new InvalidTrackingCodeException("کد پیگیری نامعتبر است.");
            }
            BuyOrder buyOrder = buyOrderOptional.get();
            if (!buyOrder.getStatus().equals(OrderStatus.PENDING)) {
                throw new OrderCancellationNotAllowedException("سفارش خرید در وضعیت " + buyOrder.getStatus() + " است و قابل کنسل کردن نیست.");
            }
            buyOrder.setActive(false);
            buyOrder.setStatus(OrderStatus.CANCELLED);
            buyOrderRepository.save(buyOrder);
        } else if (trackingCode.startsWith("s")) {
            Optional<SellOrder> sellOrderOptional = sellOrderRepository.findByTrackingCode(trackingCode);
            if (sellOrderOptional.isEmpty()) {
                throw new InvalidTrackingCodeException("کد پیگیری نامعتبر است.");
            }
            SellOrder sellOrder = sellOrderOptional.get();
            if (!sellOrder.getStatus().equals(OrderStatus.PENDING)) {
                throw new OrderCancellationNotAllowedException("سفارش خرید در وضعیت " + sellOrder.getStatus() + " است و قابل کنسل کردن نیست.");
            }
            sellOrder.setActive(false);
            sellOrder.setStatus(OrderStatus.CANCELLED);
            sellOrderRepository.save(sellOrder);
        }
        return false;
    }
}
