package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class ManagerCannotBeDeletedException extends BusinessException {

    public ManagerCannotBeDeletedException(String teams) {
        super(HttpStatus.FORBIDDEN.value(), "Given user is a team leader, so they cannot be deleted! First select new team leader for teams: " + teams);
    }
}