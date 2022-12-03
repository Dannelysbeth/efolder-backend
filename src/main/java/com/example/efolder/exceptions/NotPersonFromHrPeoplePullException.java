package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class NotPersonFromHrPeoplePullException extends BusinessException{
    public NotPersonFromHrPeoplePullException() {
        super(HttpStatus.FORBIDDEN.value(), "Nie masz prawa dostępu do informacji o zatrudnieniu tej osoby!");
    }
}
