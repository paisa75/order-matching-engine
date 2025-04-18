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
@Table(name = "SELL-ORDER-DETAILS", indexes = {
        @Index(name = "idx_price_asc_id_asc", columnList = "price Asc, id ASC")
})
public class SellOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sell_order_seq")
    @SequenceGenerator(name = "sell_order_seq", sequenceName = "sell_order_seq", allocationSize = 1)
    private Long id;

    @Column
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "tracking_code", unique = true, nullable = false, updatable = false)
    private String trackingCode;

    @Column
    protected BigDecimal price;

    @Column
    protected Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
