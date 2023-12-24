package com.example.javafx_lab5;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ThreadsGUI.fxml"));
        primaryStage.setTitle("папатоки");
        primaryStage.setScene(new Scene(root, 550, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
