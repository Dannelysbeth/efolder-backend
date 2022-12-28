package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends BusinessException {
    public EmailExistsException() {
        super(HttpStatus.BAD_REQUEST.value(), "Użytkownik z podanym adresem e-mail już istnieje");
    }
}
