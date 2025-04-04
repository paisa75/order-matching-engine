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
@Table(name = "BUY-ORDER-DETAILS")
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buy_order_seq")
    @SequenceGenerator(name = "buy_order_seq", sequenceName = "buy_order_seq", allocationSize = 1)
    private Long id;

    @Column
    private Boolean active;

    @Column
    protected BigDecimal price;

    @Column
    protected Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
