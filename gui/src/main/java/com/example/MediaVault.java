package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// import javafx.scene.Group;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.paint.Color;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.Background;

// import javafx.scene.layout.*;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MediaVault extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mediavault-view"), 640, 480);

        // scene.getRoot().setStyle

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}