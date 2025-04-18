package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.OrderStatus;
import com.tosan.ome.repository.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    List<SellOrder> findAllByOrderByPriceAscIdAsc();

    List<SellOrder> findByActiveTrueAndStatusNotOrderByPriceAscIdAsc(OrderStatus status);

    Optional<SellOrder> findByTrackingCode(String trackingCode);
}
