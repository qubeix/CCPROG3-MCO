package com.example.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.MediaVault;
import com.example.model.*;

import javafx.application.Platform;

public class MediaVaultController {

    private static final String USER_FILE = "./users.txt";

    private static User[] users = new User[100];
    private static int userCount = 0;
    private static User currentUser = null;

    /**
     * Switches the application view to the login screen.
     * 
     * Resests the root layout to the login-view.fxml file, effectively redirecting
     * the user to the login interface. This is triggered when the user needs to
     * authenticate or re-authenticate.
     * 
     * @throws IOException if the FXML resource for the login view cannot be loaded
     * @FXML This method is bound to the UI element that triggers the login action.
     */
    @FXML
    private void switchToLogin() throws IOException {
        // trigger login
        MediaVault.setRoot("login-view");
    }

    /**
     * Switches the application view to the "Create Account" screen.
     * 
     * Resets the root layout to the createAccount-view.fxml file, allowing new
     * users to the register and create an account
     * 
     * @throws IOException if the FXML resource for the create account view cannot
     *                     be loaded
     * @FXML This method is bound to the UI element that triggers the create account
     *       action.
     */
    @FXML
    private void switchToCreateAccount() throws IOException {
        // trigger createAccount
        MediaVault.setRoot("createAccount-view");
    }

    /**
     * Closes the application
     * 
     * Involes Platform.exit to terminate teh JavaFx application and release all
     * associated resources. This action is triggered when the user selects the
     * "Closes" option
     * 
     * @throws IOException IOException included for consistency with other
     *                     navigation methods, though no FXML loading occurs in this
     *                     case.
     * @FXML This method is bound to the UI element that triggers the close action
     */
    @FXML
    private void close() throws IOException {
        // trigger close
        Platform.exit();
    }

    /**
     * Retrieves the list of all registered users.
     * 
     * Returns the static users array, which contains teh currently stored User
     * object in the application
     * 
     * @return an array of User objects representing all users
     */
    public static User[] getUsers() {
        return users;
    }

    /**
     * Retrieves the total number of registered users.
     * 
     * Returns the static userCount value, which trakcs how many User objects are
     * currently stored in the application
     * 
     * @return the number of users currently registered
     */
    public static int getUserCount() {
        return userCount;
    }

    /**
     * updates the total number of registered users.
     * 
     * Returns the static userCount value to the specified count, which represents
     * how many User object are currently stored in the application
     * 
     * @return count the new total number of users
     */
    public static void setUserCount(int count) {
        userCount = count;
    }

    /**
     * Adds a new user to the application.
     * 
     * Stores the given User object in the static users array, increments the
     * userCount, and persists the new user by calling FileManager.addUsers with the
     * designated user file.
     * 
     * @param newUser the User object to be added
     * @throws IOException if an error occurs while writing the user data to the
     *                     file
     */
    public static void addUser(User newUser) throws IOException {
        users[userCount] = newUser;
        userCount++;
        FileManager.addUsers(USER_FILE, newUser);
    }

    /**
     * Retrieves the currently logged-in user.
     * 
     * Returns the static currentUser value, which represents the user who is
     * currently authenticated in the application
     * 
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void removeUser(String username) {
        int indexToRemove = -1;
        for (int i = 0; i < userCount && indexToRemove == -1; i++) {
            if (users[i].getUsername().equals(username)) {
                indexToRemove = i;
            }
        }

        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < userCount - 1; i++) {
                users[i] = users[i + 1];
            }
            users[userCount - 1] = null;
            userCount--;
        }
    }

}
