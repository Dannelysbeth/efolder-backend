package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends BusinessException {
    public RoleNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Role for given name not found");
    }
}
