package com.example.controller;

// import com.example.model.Library;
// import com.example.model.User;

import com.example.model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MainMenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextArea outputArea;

    private User currentUser;
    private Library library;

    @FXML
    private void initialize(){
        currentUser = MediaVaultController.getCurrentUser();
        if(currentUser != null){
            library = currentUser.getLibrary();
            welcomeLabel.setText("Welcome, " + currentUser.getName() + "!");
        }
        else{
            welcomeLabel.setText("Welcome!");
        }
    }

    //adding the entries 
    @FXML
    private void handleAddBook(){
        //fill in 
    }

    
}
