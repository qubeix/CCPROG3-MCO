package com.example;

import java.io.IOException;

import com.example.controller.UserController;
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

    /** The main application scene used for switching views. */
    private static Scene scene;

    /** Path to the user data file. */
    private static final String USER_FILE = "./users.txt";

    /**
     * starts the JavaFX application, loads users, and initalizes the main scene
     * 
     * @param stage teh primary stage for this application
     * @throws IOException if an error occurs while loading user data or FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        User[] users = com.example.controller.UserController.getUsers();

        System.out.println("Users file location: " + new java.io.File(FileManager.getUserFile()).getAbsolutePath());

        int loadedCount = FileManager.loadUsers(USER_FILE, users);
        UserController.setUserCount(loadedCount);
        System.out.println("Loaded " + loadedCount + "users at startup");

        scene = new Scene(loadFXML("mediavault-view"), 800, 500);

        stage.setTitle("Group 9 Go-Quin");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * sets the root of the current scene to the given FXML layout
     * 
     * @param fxml the name of the FXML file (without extension)
     * @throws IOException if the FXML cannot be loaded
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load an FXML file from the view package
     * 
     * @param fxml the name of the FXML fiel (wwithout extension)
     * @return the loaded Parent node
     * @throws IOException if the FXML can not be loaded
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launches the JavaFX application
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}