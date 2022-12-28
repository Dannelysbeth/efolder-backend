package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class TeamIncludesEmployeesException extends BusinessException {
    public TeamIncludesEmployeesException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Zespół posiada pracowników, więc nie może zostać usunięty");
    }
}
