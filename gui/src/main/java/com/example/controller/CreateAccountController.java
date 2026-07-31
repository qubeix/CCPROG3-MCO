package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {

    private static final String USER_FILE = "./users.txt";

    @FXML
    private Button createButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordVisibleField;

    @FXML
    private PasswordField verifyPasswordField;

    @FXML
    private TextField verifyPasswordVisibleField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private CheckBox showVerifyPasswordCheckBox;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private Label verifyPasswordErrorLabel;

    @FXML
    private void createAccount() throws IOException {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String verifyPassword = verifyPasswordField.getText();

        User[] users = MediaVaultController.getUsers();
        int userCount = MediaVaultController.getUserCount();

        //User newUser = null;

        if(passwordField.isVisible()){
            password = passwordField.getText();
        }
        else{
            password = passwordVisibleField.getText();
        }

        if(verifyPasswordField.isVisible()){
            verifyPassword = verifyPasswordField.getText();
        }
        else{
            verifyPassword = verifyPasswordVisibleField.getText();
        }

        boolean isUsernameTaken = false; 

        for (int i = 0; i < userCount && !isUsernameTaken; i++) {
            if (users[i].getUsername().equals(username)) {
                isUsernameTaken = true;
            }
        }

        boolean isPasswordMatch = password.equals(verifyPassword);
        //boolean containsSspace = name.contains(" ") == false && username.contains(" ") || password.contains(" ") || verifyPassword.contains(" ");
        boolean invalidSpaces = username.contains(" ") || password.contains(" ") || verifyPassword.contains(" ");

        usernameErrorLabel.setVisible(false);
        verifyPasswordErrorLabel.setVisible(false);

        if(username.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()){
            usernameErrorLabel.setText("Please fill in all fields");
            usernameErrorLabel.setVisible(true);
        }
        else if(invalidSpaces){
            verifyPasswordErrorLabel.setText("Username and password cannot contain spaces.");
            verifyPasswordErrorLabel.setVisible(true);
        }
        else if(isUsernameTaken){
            usernameErrorLabel.setText("This username already exist. Please choose a new username");
            usernameErrorLabel.setVisible(true);
        }
        else if(!isPasswordMatch){
            verifyPasswordErrorLabel.setText("Your password does not match. Please try again");
            verifyPasswordErrorLabel.setVisible(true);
        }
        else{
            User newUser = new User(username, password, name);
            MediaVaultController.addUser(newUser);
            MediaVault.setRoot("main-menu-view");
        }
    }

    @FXML
    private void toggleShowPassword() {
        if (showPasswordCheckBox.isSelected()) {
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } else {
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
        }
    }

    @FXML
    private void toggleShowVerifyPassword() {
        if (showVerifyPasswordCheckBox.isSelected()) {
            verifyPasswordVisibleField.setText(verifyPasswordField.getText());
            verifyPasswordVisibleField.setVisible(true);
            verifyPasswordVisibleField.setManaged(true);
            verifyPasswordField.setVisible(false);
            verifyPasswordField.setManaged(false);
        } else {
            verifyPasswordField.setText(verifyPasswordVisibleField.getText());
            verifyPasswordField.setVisible(true);
            verifyPasswordField.setManaged(true);
            verifyPasswordVisibleField.setVisible(false);
            verifyPasswordVisibleField.setManaged(false);
        }
    }

    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}
