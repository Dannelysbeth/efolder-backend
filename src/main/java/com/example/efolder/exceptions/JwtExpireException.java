package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class JwtExpireException extends BusinessException {

    public JwtExpireException() {
        super(HttpStatus.FORBIDDEN.value(), "JWT expired!");
    }
}
