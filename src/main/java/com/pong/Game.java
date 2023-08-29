package com.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Game extends Canvas
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * 9/16;
    public boolean isRunning = false;
    private GraphicsContext gc;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private UserInput userInput;

    public Game()
    {
        canvasSetup();
        new Window("not pong", this);
        init();
        // this.start();
    }

    private void init() 
    {
        ball = new Ball();
        leftPaddle = new Paddle(Color.RED, true);
        rightPaddle = new Paddle(Color.BLUE, false);
        userInput = new UserInput(leftPaddle, rightPaddle, this);
    }

    private void canvasSetup() 
    {
        gc = this.getGraphicsContext2D();
        this.setFocusTraversable(true);
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
    }

    AnimationTimer gameLoop = new AnimationTimer() 
    {
        // long lastFrameTime = System.nanoTime();
        // double amountOfTicks = 144;
        // double nanoSeconds = 1000000000 / amountOfTicks;
        // double secondsSinceLastFrame = 0;
        long firstFrameTime = System.currentTimeMillis();
        long currentFrameTime;
        int frames = 0;

        @Override
        public void handle(long now)
        {
            if (isRunning) 
            {
                draw();
                update();
            }

            frames++;
            currentFrameTime = System.currentTimeMillis();
            if (currentFrameTime - firstFrameTime > 1000)
            {
                firstFrameTime += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    };

    private void draw()
    {
        drawBackground();
        ball.draw(gc);
        leftPaddle.draw(gc);
        rightPaddle.draw(gc);
    }

    private void drawBackground()
     {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(4);
        gc.setLineDashes(16);
        gc.strokeLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
    }

    private void update() 
    {
        ball.update(leftPaddle, rightPaddle);
        leftPaddle.update(ball);
        rightPaddle.update(ball);
    }

    public void start()
    {
        gameLoop.start();
        isRunning = true;
    }

    // public void stop()
    // {
    //     isRunning = false;
    // }

    public static int sign(double d)
    {
       if (d <= 0)
       return -1;
       else return 1;
    }
}
