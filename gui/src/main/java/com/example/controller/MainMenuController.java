package com.example.controller;

import java.io.IOException;

import com.example.model.*;
import com.example.MediaVault;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class MainMenuController {

    @FXML private Label welcomeLabel;

    @FXML private VBox sidebarBox;
    @FXML private ScrollPane sidebarScroll;
    @FXML private AnchorPane centerPane;

    @FXML private TextArea outputArea;

    @FXML private Button homeButton;
    @FXML private Button addBookButton;
    @FXML private Button addAlbumButton;
    @FXML private Button addSeriesButton;
    @FXML private Button removeEntryButton;
    @FXML private Button rateReviewEntry;
    @FXML private Button rateEntryButton1;
    @FXML private Button updateProgressButton;
    @FXML private Button viewAllButton;
    @FXML private Button viewSummaryButton;
    @FXML private Button logoutButton;

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

        applyPanelBackgrounds();
        stylizeScrollbar();
    }

    private void applyPanelBackgrounds() {
        sidebarBox.setBackground(new Background(
            new BackgroundFill(Color.rgb(255, 255, 255, 0.35), CornerRadii.EMPTY, Insets.EMPTY)));

        centerPane.setBackground(new Background(
            new BackgroundFill(Color.rgb(255, 255, 255, 0.55), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void stylizeScrollbar() {
        sidebarScroll.setStyle("-fx-background-color: transparent;");

        Platform.runLater(() -> {
            Node track = sidebarScroll.lookup(".scroll-bar .track");
            if (track != null) {
                track.setStyle("-fx-background-color: transparent;");
            }
            Node thumb = sidebarScroll.lookup(".scroll-bar .thumb");
            if (thumb != null) {
                thumb.setStyle("-fx-background-color: rgba(0,0,0,0.3); -fx-background-radius: 4;");
            }
            for (String selector : new String[]{".increment-button", ".decrement-button",
                                                 ".increment-arrow", ".decrement-arrow"}) {
                Node n = sidebarScroll.lookup(selector);
                if (n != null) {
                    n.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
                }
            }
        });
    }

    @FXML
    private void returnHome() {
        // fill in
    }

    @FXML
    private void handleAddBook() throws IOException {
        // fill in
    }

    @FXML
    private void handleAddAlbum() throws IOException {
        // fill in
    }

    @FXML
    private void handleAddSeries() {
        // fill in
    }

    @FXML
    private void handleRemoveEntry() {
        // fill in
    }

    @FXML
    private void handleRateEntry() {
        // fill in
    }

    @FXML
    private void handleReviewEntry() {
        // fill in
    }

    @FXML
    private void handleUpdateProgress() {
        // fill in
    }

    @FXML
    private void handleViewAll() throws IOException {
        outputArea.setText(library.getAllEntriesText());
    }

    @FXML
    private void handleViewSummary() throws IOException {
        outputArea.setText(library.getSummaryText());
    }

    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}