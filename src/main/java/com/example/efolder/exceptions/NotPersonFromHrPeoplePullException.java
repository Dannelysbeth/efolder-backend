package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotPersonFromHrPeoplePullException extends BusinessException{
    public NotPersonFromHrPeoplePullException() {
        super(HttpStatus.FORBIDDEN.value(), "You are not allowed to access this person's employment information!");
    }
}
