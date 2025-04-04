package com.tosan.ome.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER-DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true, length = 20)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SellOrder> sellOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BuyOrder> buyOrders;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<CompletedOrders> completedOrders;
}
