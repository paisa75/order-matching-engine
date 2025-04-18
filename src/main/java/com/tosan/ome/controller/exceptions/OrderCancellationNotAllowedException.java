package com.tosan.ome.controller.exceptions;

public class OrderCancellationNotAllowedException extends BusinessException{
    public OrderCancellationNotAllowedException(String message) {
        super(message);
    }
}
