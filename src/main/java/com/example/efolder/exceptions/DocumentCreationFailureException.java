package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class DocumentCreationFailureException extends BusinessException{
    public DocumentCreationFailureException() {

        super(HttpStatus.NO_CONTENT.value(), "Dokument nie został utworzony");
    }
}
