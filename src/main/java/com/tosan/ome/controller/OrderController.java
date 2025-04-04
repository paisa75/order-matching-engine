package com.tosan.ome.controller;

import com.tosan.ome.controller.dtos.OrderDto;
import com.tosan.ome.service.OrderServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort orderServicePort;

    @PostMapping("/sell")
    public ResponseEntity<OrderDto> sellOrder(@RequestBody @Valid OrderDto orderDto, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(orderServicePort.creatSellOrder(orderDto, username));
    }

    @PostMapping("/Buy")
    public ResponseEntity<OrderDto> buyOrder(@RequestBody @Valid OrderDto orderDto, Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(orderServicePort.creatBuyOrder(orderDto, username));
    }
}
