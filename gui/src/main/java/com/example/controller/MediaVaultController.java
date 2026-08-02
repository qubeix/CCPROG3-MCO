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
}
