package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotAssignedToUserException extends BusinessException {
    public RoleNotAssignedToUserException(String username, String roleName) {
        super(HttpStatus.BAD_REQUEST.value(), "Role " + roleName + " is not assigned to user " + username + ".");
    }
}
