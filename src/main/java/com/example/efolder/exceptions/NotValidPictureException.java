package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidPictureException extends BusinessException {
    public NotValidPictureException() {
        super(HttpStatus.BAD_REQUEST.value(), "Podany plik nie jest prawidłowym obrazem! Proszę wybrać plik z rozszerzeniem JPEG lub PNG.");
    }
}