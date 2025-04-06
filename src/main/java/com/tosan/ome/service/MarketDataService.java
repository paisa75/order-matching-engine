package com.tosan.ome.service;

import com.tosan.ome.repository.entity.MarketPrice;
import com.tosan.ome.repository.repositories.MarketPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MarketDataService implements MarketDataServicePort {
    private final MarketPriceRepository marketPriceRepository;

    @Override
    public BigDecimal getMarketPrice() {
        MarketPrice marketPrice = marketPriceRepository.findTop1ByOrderByTimestampDesc();
        return marketPrice.getMarketPrice();
    }
}
