package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class DocumentIsTooBigException extends BusinessException {
    public DocumentIsTooBigException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "File exceeds maximum size of 10MB!");
    }
}