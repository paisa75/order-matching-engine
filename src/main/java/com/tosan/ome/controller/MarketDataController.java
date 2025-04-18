package com.tosan.ome.controller;

import com.tosan.ome.service.MarketDataServicePort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.tosan.ome.controller.RestBasePath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/market")
@AllArgsConstructor
public class MarketDataController {

    private final MarketDataServicePort marketDataServicePort;

    @GetMapping("/price")
    public BigDecimal getMarketPrice() {
        return marketDataServicePort.getMarketPrice();
    }
}
