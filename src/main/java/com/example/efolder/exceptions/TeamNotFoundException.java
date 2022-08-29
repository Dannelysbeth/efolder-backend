package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends BusinessException{
    public TeamNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Team for given name not found");
    }
}
