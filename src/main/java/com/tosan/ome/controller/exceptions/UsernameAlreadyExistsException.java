package com.tosan.ome.controller.exceptions;

public class UsernameAlreadyExistsException extends BusinessException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}