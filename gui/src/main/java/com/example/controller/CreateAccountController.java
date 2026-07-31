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

        User newUser = null;

        boolean isUsernameTaken = false; // check if the username already exist

        for (int i = 0; i < userCount && !isUsernameTaken; i++) {
            if (users[i].getUsername().equals(username)) {
                isUsernameTaken = true;
            }
        }

        if (isUsernameTaken) {
            usernameErrorLabel.setVisible(true);
            usernameErrorLabel.setText("This username already exist. Please choose a new username");
        } 
        else {
            newUser = new User(username, password, name);
            MediaVaultController.addUser(newUser);
            usernameErrorLabel.setText("");
            MediaVault.setRoot("main-menu-view");
        }

        // boolean isPasswordMatch = false;

        // if(isPasswordMatch){
        //     verifyPasswordErrorLabel.setVisible(true);
        //     verifyPasswordErrorLabel.setText("Your password does not match, please try again");
        // }

        boolean isPasswordMatch = false;

        if(isPasswordMatch.equals(verifyPassword)){
            verifyPasswordErrorLabel.setVisible(true);
            verifyPasswordErrorLabel.setText("Your password does not match, please try again");
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

    // @FXML
    // private void toggleShowVerifyPassword(){
    //     if(showVerifyPasswordCheckBox.isSelected()){
    //         verifyPasswordField.setText(verifyPasswordField.getText());
    //         verifyPasswordField.setVisible(true);
    //         verifyPasswordField.setManaged(true);
    //         verifyPasswordField.setVisible(false);
    //         verifyPasswordField.setManaged(false);
    //     }
    // }

    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}
