package com.tosan.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ORDER")
public abstract class Order implements Comparable<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;

    @Column(nullable = false)
    protected BigDecimal price;

    @Column(nullable = false)
    protected Integer quantity;

    // Constructor without parameters for JPA
    public Order() {
    }

    // Constructor with parameters
    public Order(BigDecimal price, Integer quantity) {
        this.id = generateId();
        this.price = price;
        this.quantity = quantity;
    }


    @Override
    public abstract int compareTo(Order order);

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    protected abstract String generateId();

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
