package com.example.efolder.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private int status;
    private String message;

    public BusinessException(int status, String message){
        super(message);
        this.message = message;
        this.status = status;
    }
}
