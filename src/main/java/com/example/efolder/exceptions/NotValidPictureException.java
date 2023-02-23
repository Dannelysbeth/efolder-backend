package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidPictureException extends BusinessException {
    public NotValidPictureException() {
        super(HttpStatus.BAD_REQUEST.value(), "Provided file is not a valid picture! Please select a picture with a JPEG or PNG extension.");
    }
}