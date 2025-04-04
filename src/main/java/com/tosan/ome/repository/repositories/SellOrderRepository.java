package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder, Long> {
    List<SellOrder> findAllByOrderByPriceAscIdAsc();
}
