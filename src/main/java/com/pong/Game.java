package com.pong;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Game extends Canvas
{
    public static final int WIDTH = 1920;
    public static final int HEIGHT = WIDTH * 9/16;
    public boolean isRunning = false;
    private Thread gameThread;
    private Canvas canvas;
    private GraphicsContext gc;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    public Game()
    {
        canvasSetup();
        new Window("not pong", this);
        init();
        // this.start();
    }

    private void init() 
    {
    }

    private void canvasSetup() 
    {
        gc = this.getGraphicsContext2D();
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
    }

    AnimationTimer gameLoop = new AnimationTimer() 
    {

        long lastFrameTime = System.nanoTime();
        double amountOfTicks = 144.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double secondsSinceLastFrame = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        @Override
        public void handle(long now)
        {

            if (isRunning) 
            {
                draw();

                secondsSinceLastFrame += (now - lastFrameTime) / nanoSeconds;
                lastFrameTime = now;

                while (secondsSinceLastFrame > 0)
                {
                    update();
                    secondsSinceLastFrame--;
                }

            }

            frames++;
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    };

    private void draw()
    {
        drawBackground();
    }

    private void drawBackground()
     {
        gc.setFill(Color.CADETBLUE);
        gc.fillRect((WIDTH/2) - 5, (HEIGHT/2) + 5, 50, 50);
    }

    private void update() 
    {

    }

    public void start()
    {
        gameLoop.start();
        isRunning = true;
    }

    public void stop()
    {
        try {
            gameThread.join();
            isRunning = false;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}