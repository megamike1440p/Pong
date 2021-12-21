package com.pong;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class UserInput{
    private boolean leftUp = false;
    private boolean leftDown = false;
    private boolean rightUp = false;
    private boolean rightDown = false;


    public UserInput(Paddle leftPaddle, Paddle rightPaddle, Game game)
    {
        initControls(leftPaddle, rightPaddle, game);
    }


    private void initControls(Paddle leftPaddle, Paddle rightPaddle, Game game) 
    {
        System.out.println("Controls initialized");
        game.getScene().setOnKeyPressed(e->
        {
               if (e.getCode() == KeyCode.W)
                {
                    leftUp = true;
                    leftPaddle.move(1);
                    System.out.println("Up pressed");
                }
                if (e.getCode() == KeyCode.S)
                {
                    leftDown = true;
                    leftPaddle.move(-1);
                }
                if (e.getCode() == KeyCode.UP)
                {
                    rightUp = true;
                    rightPaddle.move(1);
                    System.out.println("Up pressed");
                }
                if (e.getCode() == KeyCode.DOWN)
                {
                    rightDown = true;
                    rightPaddle.move(-1);
                }
        });

        game.getScene().setOnKeyReleased(e->
        {
            if (e.getCode() == KeyCode.W)
             {
                 leftUp = false;
                 leftPaddle.stop();
             }
             if (e.getCode() == KeyCode.S)
             {
                 leftDown = false;
                 leftPaddle.stop();
             }
             if (e.getCode() == KeyCode.UP)
             {
                 rightUp = false;
                 rightPaddle.stop();
                 System.out.println("Up pressed");
             }
             if (e.getCode() == KeyCode.DOWN)
             {
                 rightDown = false;
                 rightPaddle.stop();
             }
     });
        // if (!leftUp && !leftDown)
		// 	leftPaddle.stop();
		// if (!rightUp && !rightDown)
		// 	rightPaddle.stop();
    }

}
