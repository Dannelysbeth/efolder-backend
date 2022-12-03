package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidDocumentExtensionException extends BusinessException{
    public NotValidDocumentExtensionException() {
        super(HttpStatus.BAD_REQUEST.value(),
                "Podany plik nie jest prawidłowym dokumentem! Proszę wybrać plik z rozszerzeniem PDF.");
    }
}