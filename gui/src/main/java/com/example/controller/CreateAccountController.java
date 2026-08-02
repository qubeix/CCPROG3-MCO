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
 * Controller class for handling account creation in the GUI.
 * Provides methods for validating input, toggling password visibility,
 * and navigating between views.
 */
public class CreateAccountController {

    /** Text field for entering the user's full name. */
    @FXML
    private TextField nameField;

    /** Text field for entering the desired username. */
    @FXML
    private TextField usernameField;

    /** Hidden password field for entering the account password. */
    @FXML
    private PasswordField passwordField;

    /** Visible text field for showing the account password when toggled. */
    @FXML
    private TextField passwordVisibleField;

    /** Hidden password field for confirming the account password. */
    @FXML
    private PasswordField confirmPasswordField;

    /** Visible text field for showing the confirmation password when toggled. */
    @FXML
    private TextField confirmPasswordVisibleField;

    /** Checkbox to toggle visibility of the password field. */
    @FXML
    private CheckBox showPasswordCheckBox;

    /** Checkbox to toggle visibility of the confirm password field. */
    @FXML
    private CheckBox showConfirmPasswordCheckBox;

    /** Label for displaying username-related error messages. */
    @FXML
    private Label usernameErrorLabel;

    /** Label for displaying confirm password-related error messages. */
    @FXML
    private Label confirmPasswordErrorLabel;

    /**
     * Accepts name, username, password, and confirmation of password.
     * Performs validation to ensure:
     * - all fields are filled
     * - username and password do not contain spaces
     * - password and confirmation password match
     *
     * If validation fails, error messages are displayed.
     * If validation succeeds, creates a new User object, adds it to the system,
     * sets the new user as the current user, and proceeds to the main menu view.
     *
     * @throws IOException if the FXML resource for the main menu view cannot be loaded
     */
    @FXML
    private void createAccount() throws IOException {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        User[] users = UserController.getUsers();
        int userCount = UserController.getUserCount();

        if (passwordField.isVisible()) {
            password = passwordField.getText();
        } else {
            password = passwordVisibleField.getText();
        }

        if (confirmPasswordField.isVisible()) {
            confirmPassword = confirmPasswordField.getText();
        } else {
            confirmPassword = confirmPasswordVisibleField.getText();
        }

        boolean isUsernameTaken = false;

        for (int i = 0; i < userCount && !isUsernameTaken; i++) {
            if (users[i].getUsername().equals(username)) {
                isUsernameTaken = true;
            }
        }

        boolean isPasswordMatch = password.equals(confirmPassword);
        boolean invalidSpaces = username.contains(" ") || password.contains(" ") || confirmPassword.contains(" ");

        usernameErrorLabel.setVisible(false);
        confirmPasswordErrorLabel.setVisible(false);

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            usernameErrorLabel.setText("Please fill in all fields");
            usernameErrorLabel.setVisible(true);
        } else if (invalidSpaces) {
            confirmPasswordErrorLabel.setText("Username and password cannot contain spaces.");
            confirmPasswordErrorLabel.setVisible(true);
        } else if (isUsernameTaken) {
            usernameErrorLabel.setText("This username already exist. Please choose a new username");
            usernameErrorLabel.setVisible(true);
        } else if (!isPasswordMatch) {
            confirmPasswordErrorLabel.setText("Your password does not match. Please try again");
            confirmPasswordErrorLabel.setVisible(true);
        } else {
            User newUser = new User(username, password, name);
            UserController.addUser(newUser);
            UserController.setCurrentUser(newUser);
            MediaVault.setRoot("main-menu-view");
        }
    }

    /**
     * Toggles the visibility of the password field between hidden and visible modes
     * 
     * When "Show Password" checkbox is triggered, the hidden password field is
     * replaced
     * with a visible text field showing the current password. When the checkbox is
     * deselected,
     * the visible text field is hidden again and the password is restored to the
     * hidden field
     * 
     * This ensures that the user can choose whenever to view their password in
     * plaine text
     * or keep their password hidden
     */
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

    /**
     * Toggles the visibility of the password field between hidden and visible modes
     * 
     * When "Show Confirm Password" checkbox is triggered, the hidden password field
     * is replaced with a visible text field showing the current confirmation
     * password. When the checkbox is
     * deselected, the visible text field is hidden again and the password is
     * restored to the hidden field
     * 
     * This ensures that the user can choose whenever to view their password in
     * plaine text or keep their password hidden
     */
    @FXML
    private void toggleShowConfirmPassword() {
        if (showConfirmPasswordCheckBox.isSelected()) {
            confirmPasswordVisibleField.setText(confirmPasswordField.getText());
            confirmPasswordVisibleField.setVisible(true);
            confirmPasswordVisibleField.setManaged(true);
            confirmPasswordField.setVisible(false);
            confirmPasswordField.setManaged(false);
        } else {
            confirmPasswordField.setText(confirmPasswordVisibleField.getText());
            confirmPasswordField.setVisible(true);
            confirmPasswordField.setManaged(true);
            confirmPasswordVisibleField.setVisible(false);
            confirmPasswordVisibleField.setManaged(false);
        }
    }

    /**
     * Navigates back to the MediaVault login view.
     * Triggered when the "Back" button is clicked.
     * Resets the application root to the mediavault-view FXML file,
     * effectively returning the user to the login screen.
     *
     * @throws IOException if the FXML resource for the MediaVault view cannot be loaded
     */
    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}
