package ru.gb.lesson1;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {

    long lastFrameTime;
    private final MainCircles controller;

    MainCanvas(MainCircles controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Background bg = new Background();
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        controller.onCanvasRepainted(this, g, deltaTime, bg);

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastFrameTime = currentTime;
        repaint();
    }

    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }
}
