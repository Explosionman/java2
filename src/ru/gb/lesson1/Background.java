package ru.gb.lesson1;

import java.awt.*;
import java.util.Random;

public class Background {
    private Random rand = new Random();
    private static int waitToChange = 0;
    private static int r;
    private static int g;
    private static int b;

    public Background() {
        waitToChange++;
    }

    public Color getBackgroundColor() {
        if (waitToChange > 200) {
            r = rand.nextInt(256);
            g = rand.nextInt(256);
            b = rand.nextInt(256);
            waitToChange = 0;
        }
        return new Color(r, g, b);
    }
}
