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
    }

    AnimationTimer gameLoop = new AnimationTimer() {

        @Override
        public void handle(long now)
        {
            // this.requestFocus();
    
            long lastFrameTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double nanoSeconds = 1000000000 / amountOfTicks;
            double secondsSinceLastFrame = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
    
            while (isRunning)
            {
                secondsSinceLastFrame += (now - lastFrameTime) / nanoSeconds;
                lastFrameTime = now;
    
                while (secondsSinceLastFrame > 0)
                {
                    update();
                    // draw();
                    secondsSinceLastFrame--;
                }
    
                if (isRunning) 
                {
                    draw();
                }
    
                frames++;
    
                if (System.currentTimeMillis() - timer > 1000)
                {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
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
        gc.fillRect(10, 10, 60, 60);
        System.out.println("Background drawn");
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