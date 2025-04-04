package com.tosan.ome.controller;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.SellOrder;
import com.tosan.ome.repository.repositories.BuyOrderRepository;
import com.tosan.ome.repository.repositories.SellOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final SellOrderRepository sellOrderRepository;
    private final BuyOrderRepository buyOrderRepository;

    @GetMapping("/secure")
    public ResponseEntity<String> secureResource(){

        List<SellOrder> allByOrderByPriceAscIdAsc = sellOrderRepository.findAllByOrderByPriceAscIdAsc();
        List<BuyOrder> allByOrderByPriceDescIdAsc = buyOrderRepository.findAllByOrderByPriceDescIdAsc();
        return ResponseEntity.ok("Yes, Your JWT Works...");
    }
}
