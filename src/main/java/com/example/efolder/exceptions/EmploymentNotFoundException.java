package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class EmploymentNotFoundException extends BusinessException {
    public EmploymentNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Employment for user with given username doesn't exist!");
    }
}
