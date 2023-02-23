package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidDocumentExtensionException extends BusinessException {
    public NotValidDocumentExtensionException() {
        super(HttpStatus.BAD_REQUEST.value(),
                "Provided file is not a valid document! Please, select a file with a PDF extension.");
    }
}