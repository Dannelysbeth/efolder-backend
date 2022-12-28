package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;

public class ManagerCannotBeDeletedException extends BusinessException {

    public ManagerCannotBeDeletedException(String teams) {
        super(HttpStatus.FORBIDDEN.value(), "Podany użytkownik jest liderem zespołu, więc nie może zostać usunięty. Najpierw ustaw nowego lidera zespołu/ów : " + teams);
    }
}