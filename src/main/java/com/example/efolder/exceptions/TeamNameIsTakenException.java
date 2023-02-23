package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class TeamNameIsTakenException extends BusinessException {
    public TeamNameIsTakenException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Team for given name already exists!");
    }
}
