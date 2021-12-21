package com.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball{
    private int x, y;
    public static final int SIZE = 20;
    private final double SPEED = 3;
    private int xDirection = 1;
    private int yDirection = 1;

    public Ball()
    {
        x = (Game.WIDTH / 2) - (SIZE / 2);
        y = (Game.HEIGHT / 2) - (SIZE / 2);
    }

    private void reset()
    {
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;

        xDirection = Game.sign(Math.random() * 2.0 - 1);
        yDirection = Game.sign(Math.random() * 2.0 - 1);
    }

    public void changeXDir()
    {
        xDirection *= -1;
    }

    public void changeYDir()
    {
        yDirection *= -1;
    }

    public void draw(GraphicsContext gc)
    {
        if(xDirection <=0)
            gc.setFill(Color.BLUE);
        else
            gc.setFill(Color.RED);

        // gc.fillRect(x, y, SIZE, SIZE);
        gc.fillOval(x, y, SIZE, SIZE);
    }

    public void update(Paddle leftPaddle, Paddle rightPaddle) 
    {
        x += xDirection * SPEED;
        y += yDirection * SPEED;

        //COLLISION!!
        if (y + SIZE >= Game.HEIGHT || y <= 0)
            changeYDir();

        //with walls
        if (x <= 0)
        {
            rightPaddle.incrementScore();
            reset();
        }
        else if (x + SIZE >= Game.WIDTH)
        {
            leftPaddle.incrementScore();
            reset();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getYDirection() {
        return yDirection;
    }
}