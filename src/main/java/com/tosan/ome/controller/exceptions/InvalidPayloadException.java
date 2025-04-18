package com.tosan.ome.controller.exceptions;

public class InvalidPayloadException extends BusinessException {
    public InvalidPayloadException(String message) {
        super(message);
    }
}