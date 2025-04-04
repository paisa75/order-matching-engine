package com.tosan.ome.repository.repositories;

import com.tosan.ome.repository.entity.CompletedTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedOrdersRepository extends JpaRepository<CompletedTrade, Long> {

}
