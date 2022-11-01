package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class AuthException extends BusinessException{
    public AuthException() {
        super(HttpStatus.FORBIDDEN.value(), "Cannot authorize!");
    }
}
