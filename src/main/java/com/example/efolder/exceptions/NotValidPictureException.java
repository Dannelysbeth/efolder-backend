package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidPictureException extends BusinessException{
    public NotValidPictureException() {
        super(HttpStatus.BAD_REQUEST.value(), "Given file is not a valid picture! Please choose a file with an JPEG or PNG extension");
    }
}