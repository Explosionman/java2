package ru.gb.lesson2;

public class ParseToIntException extends Exception {
    private String errorOffSet;

    public ParseToIntException(String message, String errorOffSet) {
        super(message);
        this.errorOffSet = errorOffSet;
    }

    public String getErrorOffSet() {
        return errorOffSet;
    }
}
