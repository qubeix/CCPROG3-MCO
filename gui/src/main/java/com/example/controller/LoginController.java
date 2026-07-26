package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.User;

import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordVisibleField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private void toggleShowPassword(){
        if(showPasswordCheckBox.isSelected()){
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        }
        else{
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
        }
    }

    @FXML
    private void verifyLogin() throws IOException {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();

        if(passwordField.isVisible()){
            inputPassword = passwordField.getText();
        }
        else{
            inputPassword = passwordVisibleField.getText();
        }

        User[] users = MediaVaultController.getUsers();
        int userCount = MediaVaultController.getUserCount();

        int userIndex = -1;
        for (int i = 0; i < userCount && userIndex == -1; i++) {
            if (users[i].getUsername()().equals(inputUsername)) {
                userIndex = i;
            }
        }

        if (userIndex != -1 && users[userIndex].login(inputUsername, inputPassword)) {
            MediaVaultController.setCurrentUser(users[userIndex]);
            MediaVault.setRoot("main-menu-view");
        } else {
            System.out.println("Invalid username or passsword");
        }

    }
}