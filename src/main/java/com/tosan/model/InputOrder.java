package com.tosan.model;

import com.tosan.annotations.ValidOrderType;
import com.tosan.annotations.ValidPrice;
import com.tosan.annotations.ValidQuantity;

public class InputOrder {
    @ValidOrderType
    private String type;
    @ValidPrice
    private String price;
    @ValidQuantity
    private String quantity;

    public InputOrder(String type, String price, String quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
