package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class DocumentNotFoundException extends BusinessException {
    public DocumentNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Nie znaleziono dokumentu");
    }
}