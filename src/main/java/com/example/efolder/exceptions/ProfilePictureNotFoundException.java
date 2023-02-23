package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class ProfilePictureNotFoundException extends BusinessException {
    public ProfilePictureNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Profile picture not found");
    }
}