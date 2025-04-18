package com.tosan.ome.service;

import com.tosan.ome.repository.entity.MarketPrice;
import com.tosan.ome.repository.repositories.MarketPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MarketDataService implements MarketDataServicePort {
    private final MarketPriceRepository marketPriceRepository;

    @Override
    public BigDecimal getMarketPrice() {
        MarketPrice marketPrice = marketPriceRepository.findTop1ByOrderByTimestampDesc();
        return marketPrice.getMarketPrice();
    }

    @Override
    public void updateMarketPrice(BigDecimal tradePrice) {
        MarketPrice marketPrice = marketPriceRepository.findTop1ByOrderByTimestampDesc();
        if (marketPrice == null) {
            marketPrice = new MarketPrice();
        }
        marketPrice.setMarketPrice(tradePrice);

        marketPrice.setTimestamp(LocalDateTime.now());
        marketPriceRepository.save(marketPrice);
    }
}
