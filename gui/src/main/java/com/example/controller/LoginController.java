package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.User;
import com.example.controller.*;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class LoginController {

    /*
     * @FXML
     * private VBox mainmenuView; // Injects the root node of SubView1
     * 
     * @FXML
     * private MainMenuController mainMenuController; // Injects its controller
     */

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordVisibleField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Label errorLabel;

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
    private void verifyLogin() throws IOException {
        String inputUsername = usernameField.getText();
        String inputPassword;

        if (passwordField.isVisible()) {
            inputPassword = passwordField.getText();
        } else {
            inputPassword = passwordVisibleField.getText();
        }

        User[] users = MediaVaultController.getUsers();
        int userCount = MediaVaultController.getUserCount();
        System.out.println("At login attempt, userCount= " + userCount);

        int userIndex = -1;
        for (int i = 0; i < userCount && userIndex == -1; i++) {
            if (users[i].getUsername().equals(inputUsername)) {
                userIndex = i;
            }
        }

        if (userIndex != -1 && users[userIndex].login(inputUsername, inputPassword)) {
            errorLabel.setVisible(false);
            MediaVaultController.setCurrentUser(users[userIndex]);
            MediaVault.setRoot("main-menu-view");
        } else {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}