package com.pong;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window
{
    public Window(String title, Game game)
    {
        Stage primaryStage = new Stage();
        primaryStage.setOnCloseRequest(e-> System.exit(0));
        primaryStage.setResizable(false);

        primaryStage.setTitle(title);
        Pane root = new Pane();
        root.getChildren().addAll(game);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        game.start();
    }
}