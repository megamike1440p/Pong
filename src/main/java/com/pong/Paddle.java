package com.pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Paddle
{
    private static final int SPEED = 6;  
    private int score = 0;
    private Image paddleSprite;
    private boolean isLeft;
    private int x;
    private int y;
    private int width = 22;
    private int height = 85;
    private int direction;
    private Font scoreFont;

    public Paddle(Color c, boolean isLeft)
    {
        this.isLeft = isLeft;

        if (isLeft)
        paddleSprite = new Image("leftPaddle.png", width, height, false, true);
        else
        paddleSprite = new Image("rightPaddle.png", width, height, false, true);

        scoreFont = Font.font("Agency FB", FontWeight.BLACK, 50.0);
        

        if (isLeft)
            x = 0;
        else
            x = Game.WIDTH - width;

        y = Game.HEIGHT / 2 - height / 2 - +60;
    }

    public void incrementScore()
    {
        score++;
    }

	public void draw(GraphicsContext gc)
    {
        //paddle
        gc.drawImage(paddleSprite, x, y, width, height);

        //score
        gc.setFont(scoreFont);

        if (isLeft)
        {
            gc.setFill(Color.RED);
            gc.fillText(Integer.toString(score), Game.WIDTH / 2.0 - 75, 50.0);
        }
        else
        {
            gc.setFill(Color.BLUE);
            gc.fillText(Integer.toString(score), Game.WIDTH / 2.0 + 50, 50.0);
        }
    }

    public void update(Ball ball) 
    {
        if ((y - direction * SPEED >= 0) && (y - direction * SPEED <= Game.HEIGHT - height))
            y -= direction * SPEED;

        int ballX = ball.getX();
        int ballY = ball.getY();

        if ((isLeft && ballX <= width && ballY + ball.SIZE >= y && ballY <= y + height) 
        || (!isLeft && ballX + ball.SIZE >= Game.WIDTH - width && ballY + ball.SIZE >= y && ballY <= y + height))
        {
            ball.changeXDir();
                if (direction < 0 && ball.getYDirection() < 0)
                {
                    ball.changeYDir();
                }
                else if (direction > 0 && ball.getYDirection() > 0)
                {
                    ball.changeYDir();
                }
        }
    }

    public boolean getIsLeft() {
        return isLeft;
    }

    public void move(int direction) {
        this.direction = direction;
    }

    public void stop()
    {
        direction = 0;
    }
}