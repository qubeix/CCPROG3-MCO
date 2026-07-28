package com.example.controller;

// import com.example.model.Library;
// import com.example.model.User;

import java.io.IOException;

import com.example.model.*;
import com.example.controller.*;
import com.example.MediaVault;

import javafx.fxml.FXML;
import javafx.scene.control.*;

// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextArea;

public class MainMenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button addBookButton;

    @FXML
    private Button addAlbumButton;

    @FXML
    private Button addSeriesButton;

    @FXML
    private Button removeEntryButton;

    @FXML
    private Button rateEntryButton;

    @FXML
    private Button reviewEntryButton;

    @FXML
    private Button updateProgressButton;

    @FXML
    private Button viewAllButton;

    @FXML
    private Button viewSummaryButton;

    @FXML
    private Button logoutButton;

    // For handleAddBook()
    @FXML
    private TextField bookTitle;
    @FXML
    private TextField bookAuthor;

    private User currentUser;
    private Library library;

    @FXML
    private void initialize() {
        currentUser = MediaVaultController.getCurrentUser();
        if (currentUser != null) {
            library = currentUser.getLibrary();
            welcomeLabel.setText("Welcome, " + currentUser.getName() + "!");
        } else {
            welcomeLabel.setText("Welcome!");
        }
    }

    // add entries
    @FXML
    private void handleAddBook() throws IOException {
        MediaVault.setRoot("addbook-view");

        String title = bookTitle.getText();
        String author = bookAuthor.getText();
    }

    @FXML
    private void handleAddAlbum() throws IOException{
        MediaVault.setRoot("addalbum-view");

    }

    @FXML
    private void handleAddSeries() {
        // fill in
    }

    // remove entries
    @FXML
    private void handleRemoveEntry() {
        // fill in

    }

    // rate entries
    @FXML
    private void handleRateEntry() {
        // fill in
    }

    // review entries
    @FXML
    private void handleReviewEntry() {
        // fill in
    }

    // show update progress
    @FXML
    private void handleUpdateProgress() {
        // fill in
    }

    // view all
    @FXML
    private void handleViewAll() {
        outputArea.setText(library.getAllEntriesText());
    }

    // view summary
    @FXML
    private void handleViewSummary() {
        outputArea.setText(library.getSummaryText());
    }

    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}
