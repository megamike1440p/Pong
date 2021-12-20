package com.pong;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        new Game();
    }

    public static void main(String[] args) {
        launch();
    }

}