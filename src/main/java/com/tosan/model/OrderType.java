package com.tosan.model;

public enum OrderType {
    SELL_ORDER("sellOrder"),
    BUY_ORDER("buyOrder");

    public final String label;

    OrderType(String label) {
        this.label = label;
    }

    public static OrderType fromLabel(String label) {
        for (OrderType type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with label: " + label);
    }
}
