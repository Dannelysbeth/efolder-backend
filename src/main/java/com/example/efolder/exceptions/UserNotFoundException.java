package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "User for given username doesn't exists");
    }
}
