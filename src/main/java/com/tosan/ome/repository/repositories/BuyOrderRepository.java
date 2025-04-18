package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.BuyOrder;
import com.tosan.ome.repository.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    List<BuyOrder> findAllByOrderByPriceDescIdAsc();
    List<BuyOrder> findByActiveTrueAndStatusNotOrderByPriceDescIdAsc(OrderStatus status);

    Optional<BuyOrder> findByTrackingCode(String trackingCode);
}
