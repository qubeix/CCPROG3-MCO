package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.User;
import com.example.model.FileManager;

import javafx.application.Platform;

public class MediaVaultController {

    private static final String USER_FILE = "./users.txt";

    private static User[] users = new User[100];
    private static int userCount = 0;
    private static User currentUser = null;

    @FXML
    private void switchToLogin() throws IOException {
        // trigger login
        MediaVault.setRoot("login-view");
    }

    @FXML
    private void switchToCreateAccount() throws IOException {
        // trigger createAccount
        MediaVault.setRoot("createAccount-view");
    }

    @FXML
    private void close() throws IOException {
        // trigger close
        Platform.exit();

    }

    public static User[] getUsers() {
        return users;
    }

    public static int getUserCount() {
        return userCount;
    }

    public static void setUserCount(int count) {
        userCount = count;
    }

    public static void addUser(User newUser) throws IOException {
        users[userCount] = newUser;
        userCount++;
        FileManager.addUsers(USER_FILE, newUser);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

}
