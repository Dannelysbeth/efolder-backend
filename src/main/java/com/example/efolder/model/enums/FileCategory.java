package com.example.efolder.model.enums;

import com.example.efolder.exceptions.NoSuchFileCategoryException;

public enum FileCategory {
    A("A"),
    B("B"),
    C("C"),
    D("D");
    private static final String[] categories = new String[]{"A", "B", "C", "D"};

    FileCategory(String fileCategoryString){
    }

    public static FileCategory transformStringToFileCategory(String fileCategoryString){
        return switch (fileCategoryString) {
            case "A" -> A;
            case "B" -> B;
            case "C" -> C;
            case "D" -> D;
            default -> throw new NoSuchFileCategoryException();
        };
    }
    public static String[] getAllCategories(){
        return categories;
    }

}
