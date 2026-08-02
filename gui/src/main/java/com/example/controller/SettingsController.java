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

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

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
            User[] users = MediaVaultController.getUsers();
            int userCount = MediaVaultController.getUserCount();
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

    @FXML
    private void confirmDeleteAccount() {
        String enteredPassword = deleteConfirmPasswordField.getText();

        if (!enteredPassword.equals(currentUser.getPassword())) {
            deleteMessage.setStyle("-fx-text-fill: RED");
            deleteMessage.setText("Incorrect password. Account not deleted.");
        } else {
            try {
                MediaVaultController.removeUser(currentUser.getUsername());
                FileManager.rewriteAllUsers(FileManager.getUserFile(), MediaVaultController.getUsers(),
                        MediaVaultController.getUserCount());
                MediaVaultController.setCurrentUser(null);
                MediaVault.setRoot("mediavault-view");
            } catch (IOException e) {
                deleteMessage.setStyle("-fx-text-fill: RED");
                deleteMessage.setText("Something went wrong while deleting your account.");
                e.printStackTrace();
            }
        }
    }

    private void persistChanges() {
        try {
            FileManager.rewriteAllUsers(FileManager.getUserFile(), MediaVaultController.getUsers(),
                    MediaVaultController.getUserCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}