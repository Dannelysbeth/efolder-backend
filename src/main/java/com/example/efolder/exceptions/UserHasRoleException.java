package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class UserHasRoleException extends BusinessException{
    public UserHasRoleException(String roleName, String username) {

        super(HttpStatus.BAD_REQUEST.value(), roleName+" is already assigned to user " + username);
    }
}
