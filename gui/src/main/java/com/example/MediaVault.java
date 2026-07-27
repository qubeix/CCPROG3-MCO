package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class MediaVault extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //User[] users = com.example.controller.MediaVaultController.getUsers();
        //int loadedCount = com.example.controller.FileManager.loadUsers(users);
        //com.example.controller.MediaVaultController.setUserCount(loadedCount);

        scene = new Scene(loadFXML("mediavault-view"), 640, 480);

        stage.setTitle("Group 5 Go-Quin");
        stage.setScene(scene);
        stage.setResizable(true);
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