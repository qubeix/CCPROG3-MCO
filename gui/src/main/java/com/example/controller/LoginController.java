package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.User;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void verifyLogin() throws IOException {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();

        User[] users = MediaVaultController.getUsers();
        int userCount = MediaVaultController.getUserCount();

        int userIndex = -1;
        for (int i = 0; i < userCount && userIndex == -1; i++) {
            if (users[i].getLibrary().equals(inputUsername)) {
                userIndex = i;
            }
        }

        if (userIndex != -1 && users[userIndex].login(inputUsername, inputPassword)) {
            MediaVaultController.setCurrentUser(users[userIndex]);
            MediaVault.setRoot("Group 9 Go-Quin");
        } else {
            System.out.println("Invalid username or passsword");
        }

    }
}