package com.tosan.ome.controller;

import com.tosan.ome.controller.dtos.OrderDto;
import com.tosan.ome.service.OrderServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.tosan.ome.controller.RestBasePath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/order")
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

    @DeleteMapping("/cancel/{trackingCode}")
    public ResponseEntity<String> cancelOrder(@PathVariable String trackingCode) {
        boolean isCancelled = orderServicePort.cancelOrderByTrackingCode(trackingCode);
        if (isCancelled) {
            return ResponseEntity.ok("Order successfully canceled.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found or cannot be canceled.");
        }
    }
}
