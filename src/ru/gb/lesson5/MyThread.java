package ru.gb.lesson5;

public class MyThread extends Thread {
    private float[] arr;
    private int h;

    public MyThread(float[] arr, int h) {
        this.arr = arr;
        this.h = h;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
        }
    }
}
