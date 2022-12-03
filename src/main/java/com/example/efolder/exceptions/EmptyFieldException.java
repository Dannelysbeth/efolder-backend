package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyFieldException extends BusinessException{
    public EmptyFieldException(String fieldName) {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Pole "+ fieldName+" nie może być puste");
    }
}