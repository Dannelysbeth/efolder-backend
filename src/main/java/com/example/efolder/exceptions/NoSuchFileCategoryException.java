package com.example.efolder.exceptions;

import org.springframework.http.HttpStatus;


import static com.example.efolder.model.enums.FileCategory.getAllCategories;

public class NoSuchFileCategoryException extends BusinessException{
    public NoSuchFileCategoryException() {
        super(HttpStatus.BAD_REQUEST.value(), "No such file category. Available categories: "
        + String.join(", ", getAllCategories()));
    }
}
