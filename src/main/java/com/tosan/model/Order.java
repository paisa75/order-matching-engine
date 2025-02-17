package com.tosan.model;

import java.math.BigDecimal;

public abstract class Order implements Comparable<Order> {
    protected String id;
    protected BigDecimal price;
    protected Integer quantity;

    public Order(BigDecimal price, Integer quantity) {
        this.id = generateId();
        this.price = price;
        this.quantity = quantity;
    }

    // پیاده‌سازی متد compareTo برای مقایسه
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
