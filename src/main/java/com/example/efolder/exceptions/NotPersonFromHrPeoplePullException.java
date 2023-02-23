package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotPersonFromHrPeoplePullException extends BusinessException {
    public NotPersonFromHrPeoplePullException() {
        super(HttpStatus.FORBIDDEN.value(), "You dont have the permissions to view information about this user's employment!");
    }
}
