package com.pong;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage stage) {
        new Game();
    }

    public static void main(String[] args) {
        launch();
    }
}