package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameIsTakenException extends BusinessException {
    public UsernameIsTakenException() {
        super(HttpStatus.BAD_REQUEST.value(), "Username is not available!");
    }
}
