package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
;

public class CreateAccountController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void createAccount() throws IOException{
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        User[] users = MediaVaultController.getUsers();
        int userCount = MediaVaultController.getUserCount();

        boolean isUsernameTaken = false;         //check if the username already exist

        for(int i = 0; i < userCount && !isUsernameTaken; i++){
            if(users[i].getUsername().equals(username)){
                isUsernameTaken = true;
            }
        }

        if(isUsernameTaken){
            errorLabel.setText("This username already exist. Please choose a new username");
        }
        else{
            User newUser = new User(username, password, name);
            MediaVaultController.addUser(newUser);
            errorLabel.setText("");
            MediaVault.setRoot("login-view");
        }
    }

    @FXML
    private void goBack() throws IOException{
        MediaVault.setRoot("mediavault-view");
    }
}
