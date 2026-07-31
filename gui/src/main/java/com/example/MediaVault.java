package com.example;

import java.io.IOException;

import com.example.controller.MediaVaultController;
import com.example.model.FileManager;
import com.example.model.User;

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
    private static final String USER_FILE = "./users.txt";
    // private static FileManager file = new FileManager();

    @Override
    public void start(Stage stage) throws IOException {
        User[] users = com.example.controller.MediaVaultController.getUsers();

        System.out.println("Users file location: " + new java.io.File(FileManager.getUserFile()).getAbsolutePath());

        int loadedCount = FileManager.loadUsers(USER_FILE, users);
        MediaVaultController.setUserCount(loadedCount);
        System.out.println("Loaded " + loadedCount + "users at startup");

        scene = new Scene(loadFXML("mediavault-view"), 800, 500);

        stage.setTitle("Group 5 Go-Quin");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}