package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.*;

import javafx.fxml.FXML;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;
// import javafx.scene.control.CheckBox;
// import javafx.scene.control.PasswordField;
import javafx.scene.control.*;

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
    private Label errorLabel;

    @FXML
    private void createAccount() throws IOException {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

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
            errorLabel.setText("This username already exist. Please choose a new username");
        } else {
            newUser = new User(username, password, name);
            MediaVaultController.addUser(newUser);
            errorLabel.setText("");
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
            verifyPasswordVisibleField.setText(passwordField.getText());
            verifyPasswordVisibleField.setVisible(true);
            verifyPasswordVisibleField.setManaged(true);
            verifyPasswordVisibleField.setVisible(false);
            passwordField.setManaged(false);
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
