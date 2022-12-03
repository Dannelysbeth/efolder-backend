package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotMatchingPasswordException extends BusinessException{
    public NotMatchingPasswordException() {
        super(HttpStatus.BAD_REQUEST.value(), "Hasła nie pasują!");
    }
}