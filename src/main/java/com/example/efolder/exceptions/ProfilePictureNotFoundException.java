package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class ProfilePictureNotFoundException extends BusinessException {
    public ProfilePictureNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Nie znaleziono zdjęcia profilowego");
    }
}