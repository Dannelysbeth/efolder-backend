package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class TeamIncludesEmployeesException extends BusinessException {
    public TeamIncludesEmployeesException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "This teams has team members, so it cannot be deleted!");
    }
}
