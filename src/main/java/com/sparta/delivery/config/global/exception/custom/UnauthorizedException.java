package com.sparta.delivery.config.global.exception.custom;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
