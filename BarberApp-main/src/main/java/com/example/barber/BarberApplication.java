package com.example.barber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BarberApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(BarberApplication.class.getResource("ClientesView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        scene.getStylesheets().add(BarberApplication.class.getResource("style.css").toExternalForm());

        stage.setTitle("BarberControl - Panel Administrativo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}