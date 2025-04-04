package com.tosan.ome.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "COMPLETED-ORDER")
public class CompletedTrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer tradeQuantity;

    @Column
    private BigDecimal tradePrice;

    @ManyToOne
    @JoinColumn(name = "buyOrder_id", nullable = false)
    private BuyOrder buyOrder;

    @ManyToOne
    @JoinColumn(name = "sellOrder_id", nullable = false)
    private SellOrder sellOrder;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

}
