package com.tosan.ome.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING(1, "در انتظار انجام", "Pending - order is placed but not yet executed"),
    PARTIALLY_FILLED(2, "انجام جزئی", "Partially filled - part of the order has been executed"),
    FILLED(3, "تکمیل شده", "Filled - order has been fully executed"),
    CANCELLED(4, "لغو شده", "Cancelled - order has been cancelled before full execution");

    private final int code;
    private final String faDescription;
    private final String enDescription;

    public static OrderStatus fromCode(int code) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid OrderStatus code: " + code));
    }
}
