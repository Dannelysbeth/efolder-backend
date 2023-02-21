package com.example.efolder.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final int status;
    private final String message;

    public BusinessException(int status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
