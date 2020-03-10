package ru.gb.lesson2;

public class Array4x4Exception extends Exception {
    private String[] arr;

    public Array4x4Exception(String message, String[] arr) {
        super(message);
        this.arr = arr;
    }

    public int getArrayLength() {
        return arr.length;
    }
}
