package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotAssignedToUserException extends BusinessException{
    public RoleNotAssignedToUserException(String username, String roleName) {
        super(HttpStatus.BAD_REQUEST.value(), "Rola "+roleName+" nie jest przypisana do użytkownika "+username+".");
    }
}
