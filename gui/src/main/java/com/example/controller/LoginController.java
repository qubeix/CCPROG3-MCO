package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.User;
import com.example.controller.*;

import javafx.scene.layout.VBox;
import javafx.scene.control.*;

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
    private Label errorLabel;

    /**
     * Toggles the visibility of the password field between hidden and visible mode
     * 
     * when the "Show Password" checkbock is trigged, the hidden password field is
     * replaced with a visible text field showing the current password. When the
     * checkbox is deselected, the visible text field is hidden again and the
     * password is restored to a hidden field
     * 
     * @FXML This method is bound to the "Show Password" checkbox in the login form
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
     * @FXML This method is bound to the "Login" button in the login form
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
     * @FXML This method is bound to the "Back" button in the login form
     */
    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}