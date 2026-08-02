package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Controller class for handling navigation in the MediaVault entry screen.
 * Provides methods to switch between login, account creation, and closing the application.
 */
public class MediaVaultController {

    /**
     * Switches the application view to the login screen.
     * 
     * Resests the root layout to the login-view.fxml file, effectively redirecting
     * the user to the login interface. This is triggered when the user needs to
     * authenticate or re-authenticate.
     * 
     * @throws IOException if the FXML resource for the login view cannot be loaded
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
     */
    @FXML
    private void close() throws IOException {
        // trigger close
        Platform.exit();
    }
}
