package com.tosan.ome.controller.dtos;

import lombok.Data;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@Data
public class OrderDto {
    private String id;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "1.00", message = "Price must be at least 1.00")
    private BigDecimal price;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;
}
