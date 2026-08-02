package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.FileManager;
import com.example.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SettingsController {

    private static final String USER_FILE = "./users.txt";

    @FXML
    private TextField newNameField;

    @FXML
    private Label nameMessage;

    @FXML
    private TextField newUsernameField;

    @FXML
    private Label usernameMessage;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmNewPasswordField;

    @FXML
    private Label passwordMessage;

    @FXML
    private PasswordField deleteConfirmPasswordField;

    @FXML
    private TextField currentPasswordVisibleField;

    @FXML
    private TextField newPasswordVisibleField;

    @FXML
    private TextField confirmNewPasswordVisibleField;

    @FXML
    private CheckBox showCurrentPasswordCheckBox;

    @FXML
    private CheckBox showNewPasswordCheckBox;

    @FXML
    private CheckBox showConfirmNewPasswordCheckBox;

    @FXML
    private Label deleteMessage;

    private User currentUser;

    /**
     * Sets the current user for this controller.
     * 
     * Assigns the provided User object to the currentUser field,
     * allowing the controller to track and manage actions based
     * on the active user.
     *
     * @param user the User object to set as the current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Confirms and updates the current user's name
     * 
     * Retrieves the new name from the input field and confirms it.
     * If the name is empty, it displays an error message. If valid,
     * it updates the currentUser's name, persists the changes, shows
     * a success message, and clears the input field.
     *
     * @FXML This method is triggered when the user clicks the
     *       confirm button for changing their name.
     */
    @FXML
    private void confirmChangeName() {
        String newName = newNameField.getText();

        if (newName.trim().isEmpty()) {
            nameMessage.setStyle("-fx-text-fill: RED");
            nameMessage.setText("Name cannot be empty.");
        } else {
            currentUser.setName(newName);
            persistChanges();
            nameMessage.setStyle("-fx-text-fill: GREEN");
            nameMessage.setText("Name updated successfully.");
            newNameField.clear();
        }
    }

    /**
     * Confirms and updates the current user's username.
     * 
     * Retrieves the new username from the input field and validates it.
     * If the username is empty or contains spaces, it displays an error message.
     * If the username matches the current one, it shows a message that no change
     * was made to the username. Otherwise, checks if the username is already taken
     * by another user. If available, updates the currentUser's username, persists
     * the changes, shows a success message, and clears the input field. If taken,
     * displays an error message instead.
     *
     * @FXML This method is triggered when the user clicks the
     *       confirm button for changing their username.
     */
    @FXML
    private void confirmChangeUsername() {
        String newUsername = newUsernameField.getText();

        if (newUsername.trim().isEmpty() || newUsername.contains(" ")) {
            usernameMessage.setStyle("-fx-text-fill: RED");
            usernameMessage.setText("Username cannot be empty or contain spaces.");
        } else if (newUsername.equalsIgnoreCase(currentUser.getUsername())) {
            usernameMessage.setStyle("-fx-text-fill: GREEN");
            usernameMessage.setText("Username was not changed");
            newUsernameField.clear();
        } else {
            User[] users = UserController.getUsers();
            int userCount = UserController.getUserCount();
            boolean isTaken = false;

            for (int i = 0; i < userCount && !isTaken; i++) {
                if (users[i].getUsername().equals(newUsername) && users[i] != currentUser) {
                    isTaken = true;
                }
            }

            if (isTaken) {
                usernameMessage.setStyle("-fx-text-fill: RED");
                usernameMessage.setText("This username is already taken.");
            } else {
                currentUser.setUsername(newUsername);
                persistChanges();
                usernameMessage.setStyle("-fx-text-fill: GREEN");
                usernameMessage.setText("Username updated successfully.");
                newUsernameField.clear();
            }
        }
    }

    /**
     * Toggles the visibility of the current password field.
     * 
     * Switches between showing the password in plain text and hiding it
     * behind a hidden field, depending on the state of the
     * showCurrentPasswordCheckBox. Ensures that the entered password
     * remains synchronized between the visible and hidden fields.
     *
     * @FXML This method is triggered when the user checks or unchecks
     *       the "Show Password" option for the current password.
     */
    @FXML
    private void toggleShowCurrentPassword() {
        if (showCurrentPasswordCheckBox.isSelected()) {
            currentPasswordVisibleField.setText(currentPasswordField.getText());
            currentPasswordVisibleField.setVisible(true);
            currentPasswordVisibleField.setManaged(true);
            currentPasswordField.setVisible(false);
            currentPasswordField.setManaged(false);
        } else {
            currentPasswordField.setText(currentPasswordVisibleField.getText());
            currentPasswordField.setVisible(true);
            currentPasswordField.setManaged(true);
            currentPasswordVisibleField.setVisible(false);
            currentPasswordVisibleField.setManaged(false);
        }
    }

    /**
     * Toggles the visibility of the new password field.
     * 
     * Switches between showing the new password in plain text and
     * hiding it behind a hidden field, depending on the state of the
     * showNewPasswordCheckBox. Ensures that the entered password
     * remains synchronized between the visible and hidden fields.
     *
     * @FXML This method is triggered when the user checks or unchecks
     *       the "Show Password" option for the new password.
     */
    @FXML
    private void toggleShowNewPassword() {
        if (showNewPasswordCheckBox.isSelected()) {
            newPasswordVisibleField.setText(newPasswordField.getText());
            newPasswordVisibleField.setVisible(true);
            newPasswordVisibleField.setManaged(true);
            newPasswordField.setVisible(false);
            newPasswordField.setManaged(false);
        } else {
            newPasswordField.setText(newPasswordVisibleField.getText());
            newPasswordField.setVisible(true);
            newPasswordField.setManaged(true);
            newPasswordVisibleField.setVisible(false);
            newPasswordVisibleField.setManaged(false);
        }
    }

    /**
     * Toggles the visibility of the confirm new password field.
     * 
     * Switches between showing the confirmation password in plain text
     * and hiding it behind a hidden field, depending on the state of the
     * showConfirmNewPasswordCheckBox. Ensures that the entered password
     * remains synchronized between the visible and hidden fields.
     *
     * @FXML This method is triggered when the user checks or unchecks
     *       the "Show Password" option for the confirm new password field.
     */
    @FXML
    private void toggleShowConfirmNewPassword() {
        if (showConfirmNewPasswordCheckBox.isSelected()) {
            confirmNewPasswordVisibleField.setText(confirmNewPasswordField.getText());
            confirmNewPasswordVisibleField.setVisible(true);
            confirmNewPasswordVisibleField.setManaged(true);
            confirmNewPasswordField.setVisible(false);
            confirmNewPasswordField.setManaged(false);
        } else {
            confirmNewPasswordField.setText(confirmNewPasswordVisibleField.getText());
            confirmNewPasswordField.setVisible(true);
            confirmNewPasswordField.setManaged(true);
            confirmNewPasswordVisibleField.setVisible(false);
            confirmNewPasswordVisibleField.setManaged(false);
        }
    }

    /**
     * Confirms and updates the current user's password.
     * 
     * Retrieves the current, new, and confirmation password values
     * from either the masked or visible fields depending on which
     * are active. Validates the inputs by checking that the current
     * password matches the user's existing password, the new password
     * is not empty or containing spaces, and that the new password
     * matches the confirmation field. If validation passes, updates
     * the user's password, persists the changes, shows a success
     * message, and clears all password fields. If validation fails,
     * displays an appropriate error message.
     *
     * @FXML This method is triggered when the user clicks the
     *       confirm button for changing their password.
     */
    @FXML
    private void confirmChangePassword() {
        String current;
        if (currentPasswordField.isVisible()) {
            current = currentPasswordField.getText();
        } else {
            current = currentPasswordVisibleField.getText();
        }

        String newPas;
        if (newPasswordField.isVisible()) {
            newPas = newPasswordField.getText();
        } else {
            newPas = newPasswordVisibleField.getText();
        }

        String confirmPas;
        if (confirmNewPasswordField.isVisible()) {
            confirmPas = confirmNewPasswordField.getText();
        } else {
            confirmPas = confirmNewPasswordVisibleField.getText();
        }

        if (!current.equals(currentUser.getPassword())) {
            passwordMessage.setStyle("-fx-text-fill: RED");
            passwordMessage.setText("Current password is incorrect.");
        } else if (newPas.trim().isEmpty() || newPas.contains(" ")) {
            passwordMessage.setStyle("-fx-text-fill: RED");
            passwordMessage.setText("New password cannot be empty or contain spaces.");
        } else if (!newPas.equals(confirmPas)) {
            passwordMessage.setStyle("-fx-text-fill: RED");
            passwordMessage.setText("New passwords do not match.");
        } else {
            currentUser.setPassword(newPas);
            persistChanges();
            passwordMessage.setStyle("-fx-text-fill: GREEN");
            passwordMessage.setText("Password updated successfully.");
            currentPasswordField.clear();
            currentPasswordVisibleField.clear();
            newPasswordField.clear();
            newPasswordVisibleField.clear();
            confirmNewPasswordField.clear();
            confirmNewPasswordVisibleField.clear();
        }
    }

    /**
     * Confirms and deletes the current user's account.
     * 
     * Validates the entered password against the current user's password.
     * If incorrect, displays an error message.
     * If correct, attempts to remove the user from the UserController,
     * rewrites the user file to persist changes, clears the current user,
     * and redirects to the MediaVault main view. If an I/O error occurs
     * during deletion, displays an error message instead.
     *
     * @FXML This method is triggered when the user clicks the
     *       confirm button for deleting their account.
     */
    @FXML
    private void confirmDeleteAccount() {
        String enteredPassword = deleteConfirmPasswordField.getText();

        if (!enteredPassword.equals(currentUser.getPassword())) {
            deleteMessage.setStyle("-fx-text-fill: RED");
            deleteMessage.setText("Incorrect password. Account not deleted.");
        } else {
            try {
                UserController.removeUser(currentUser.getUsername());
                FileManager.rewriteAllUsers(FileManager.getUserFile(), UserController.getUsers(),
                        UserController.getUserCount());
                UserController.setCurrentUser(null);
                MediaVault.setRoot("mediavault-view");
            } catch (IOException e) {
                deleteMessage.setStyle("-fx-text-fill: RED");
                deleteMessage.setText("Something went wrong while deleting your account.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Persists all user changes to storage.
     * 
     * Rewrites the user file with the current list of users
     * and their updated information, ensuring that changes
     * such as name, username, or password updates are saved.
     * If an I/O error occurs during the process, the stack
     * trace is printed for debugging.
     */
    private void persistChanges() {
        try {
            FileManager.rewriteAllUsers(FileManager.getUserFile(), UserController.getUsers(),
                    UserController.getUserCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}