package com.example.bookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main JavaFX application class that launches the UI from FXML.
 */
public class HelloApplication extends Application {

    /**
     * Starts the JavaFX application. Loads the FXML layout and shows the stage.
     *
     * @param stage the primary stage for this application
     * @throws IOException if loading the FXML file fails
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Booking Manager");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Main entry point. Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}