package com.tosan.ome.service;

import com.tosan.ome.repository.entity.MarketPrice;

import java.math.BigDecimal;

public interface MarketDataServicePort {
    BigDecimal getMarketPrice();

    void updateMarketPrice(BigDecimal tradePrice);
}
