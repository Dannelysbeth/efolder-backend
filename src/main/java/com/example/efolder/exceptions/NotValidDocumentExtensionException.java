package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidDocumentExtensionException extends BusinessException{
    public NotValidDocumentExtensionException() {
        super(HttpStatus.BAD_REQUEST.value(),
                "Given file is not a valid document! Please choose a file with an PDF extension.");
    }
}