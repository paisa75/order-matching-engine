package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    List<BuyOrder> findAllByOrderByPriceDescIdAsc();
}
