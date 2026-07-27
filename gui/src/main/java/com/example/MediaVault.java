package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.controller.*;
import com.example.model.*;

/**
 * JavaFX App
 */
public class MediaVault extends Application {

    private static Scene scene;
    private static final String USER_FILE = "./users.txt";
    // private static FileManager file = new FileManager();

    @Override
    public void start(Stage stage) throws IOException {
        User[] users = com.example.controller.MediaVaultController.getUsers();

        int loadedCount = FileManager.loadUsers(USER_FILE, users);
        MediaVaultController.setUserCount(loadedCount);

        // int i;
        // for (i = 0; i < com.example.controller.MediaVaultController.getUserCount();
        // i++) {
        // addUsers("./" + USER_FILE, users[i]);
        // }
        // int loadedCount = com.example.model.FileManager.addUsers("./" + USER_FILE,
        // user);
        // com.example.controller.MediaVaultController.setUserCount(loadedCount);

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