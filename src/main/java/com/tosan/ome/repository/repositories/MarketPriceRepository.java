package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.MarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketPriceRepository extends JpaRepository<MarketPrice, Long> {
    MarketPrice findTop1ByOrderByTimestampDesc();
}
