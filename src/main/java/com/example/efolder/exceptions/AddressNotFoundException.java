package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends BusinessException{
    public AddressNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Adres dla danego użytkownika nie istnieje!");
    }
}
