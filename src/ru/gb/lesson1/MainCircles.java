package ru.gb.lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static int ballsInGame = 3;

    private Sprite[] sprites = new Sprite[100];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        initApplication();

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    ballsInGame++;
                    initApplication();
                } else if (e.getButton() == 3) {
                    ballsInGame--;
                    initApplication();
                }
            }
        });
        setVisible(true);
    }

    private void initApplication() {

        for (int i = 0; i < ballsInGame; i++) {
            sprites[i] = new Ball();
        }
    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime, Background bg) {
        update(canvas, deltaTime);
        render(canvas, g);
        repaintBg(canvas, bg);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < ballsInGame; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < ballsInGame; i++) {
            sprites[i].render(canvas, g);
        }
    }

    private void repaintBg(MainCanvas canvas, Background bg) {
        canvas.setBackground(bg.getBackgroundColor());
    }
}
