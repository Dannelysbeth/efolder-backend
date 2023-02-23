package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "User with given username doesn't exist");
    }
}
