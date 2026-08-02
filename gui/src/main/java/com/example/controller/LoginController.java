package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for handling user login in the GUI.
 * Provides methods for toggling password visibility, verifying credentials,
 * and navigating between views.
 */
public class LoginController {

    /** Text field for entering the username. */
    @FXML
    private TextField usernameField;

     /** Hidden password field for entering the account password. */
    @FXML
    private PasswordField passwordField;

    /** Visible text field for showing the account password when toggled. */
    @FXML
    private TextField passwordVisibleField;

    /** Checkbox to toggle visibility of the password field. */
    @FXML
    private CheckBox showPasswordCheckBox;

    /** Label for displaying login error messages. */
    @FXML
    private Label errorLabel;

    /**
     * Toggles the visibility of the password field between hidden and visible mode
     * 
     * when the "Show Password" checkbock is trigged, the hidden password field is
     * replaced with a visible text field showing the current password. When the
     * checkbox is deselected, the visible text field is hidden again and the
     * password is restored to a hidden field
     */
    @FXML
    private void togglePassword() {
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

    /**
     * Verifies the user's login credentials
     * 
     * This accepts a username and password from the login form. Performs a
     * validation by checking the entered
     * 
     * @throws IOException if the FXML resource for the main menu view can not be
     *                     loaded
     */
    @FXML
    private void verifyLogin() throws IOException {
        String inputUsername = usernameField.getText();
        String inputPassword;

        if (passwordField.isVisible()) {
            inputPassword = passwordField.getText();
        } else {
            inputPassword = passwordVisibleField.getText();
        }

        User[] users = UserController.getUsers();
        int userCount = UserController.getUserCount();
        System.out.println("At login attempt, userCount= " + userCount);

        int userIndex = -1;
        for (int i = 0; i < userCount && userIndex == -1; i++) {
            if (users[i].getUsername().equals(inputUsername)) {
                userIndex = i;
            }
        }

        if (userIndex != -1 && users[userIndex].login(inputUsername, inputPassword)) {
            errorLabel.setVisible(false);
            UserController.setCurrentUser(users[userIndex]);
            MediaVault.setRoot("main-menu-view");
        } else {
            errorLabel.setVisible(true);
        }
    }

    /**
     * Navigates back to the MediaVault login view
     * 
     * Is triggered once the "Back" button is clicked, this method resets the
     * application roomm to the mediavault-view FXML file, effectively returning the
     * user to the login screen
     * 
     * @throws IOException if the FXML resoruce for the MediaVault view can not be
     *                     loaded
     */
    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}