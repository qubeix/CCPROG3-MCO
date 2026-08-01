package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.Library;
import com.example.model.NumberTextField;
import com.example.model.User;
import com.example.controller.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private VBox sidebarBox;

    @FXML
    private ScrollPane sidebarScroll;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button homeButton;

    @FXML
    private Button addBookButton;

    @FXML
    private Button addAlbumButton;

    @FXML
    private Button addSeriesButton;

    @FXML
    private Button removeEntryButton;

    @FXML
    private Button rateReviewEntry;

    @FXML
    private Button rateEntryButton1;

    @FXML
    private Button updateProgressButton;

    @FXML
    private Button viewAllButton;

    @FXML
    private Button viewSummaryButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button logoutButton;

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
            for (String selector : new String[] { ".increment-button", ".decrement-button",
                    ".increment-arrow", ".decrement-arrow" }) {
                Node n = sidebarScroll.lookup(selector);
                if (n != null) {
                    n.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
                }
            }
        });
    }

    @FXML
    private void returnHome() {
        centerPane.getChildren().setAll(outputArea);
        outputArea.setVisible(true);
        outputArea.setManaged(true);
    }

    // BOOK
    @FXML
    private void handleAddBook() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/book-view.fxml"));
            Parent bookPaneWithController = loader.load();
            BookController bookController = loader.getController();
            bookController.setLibrary(library);

            centerPane.getChildren().setAll(bookPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ALBUM
    @FXML
    private void handleAddAlbum() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/album-view.fxml"));
            Parent albumPaneWithController = loader.load();
            AlbumController albumController = loader.getController();
            albumController.setLibrary(library);

            centerPane.getChildren().setAll(albumPaneWithController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SERIES
    @FXML
    private void handleAddSeries() {
        // fill in
    }

    @FXML
    private void continueAddSeries() {

    }

    // REMOVE
    @FXML
    private void handleRemoveEntry() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/removeEntry-view.fxml"));
            Parent removePaneWithController = loader.load();
            RemoveEntryController entryController = loader.getController();
            entryController.setLibrary(library);

            centerPane.getChildren().setAll(removePaneWithController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RATE
    @FXML
    private void handleRateEntry() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/rate-view.fxml"));
            Parent ratePaneWithController = loader.load();
            RateController rateController = loader.getController();
            rateController.setLibrary(library);

            centerPane.getChildren().setAll(ratePaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REVIEW
    @FXML
    private void handleReviewEntry() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/review-view.fxml"));
            Parent reviewPaneWithController = loader.load();
            ReviewEntryController controller = loader.getController();
            controller.setLibrary(library);

            centerPane.getChildren().setAll(reviewPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE PROGRESS
    @FXML
    private void handleUpdateProgress() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    MediaVault.class.getResource("/com/example/view/updateProgress-view.fxml"));
            Parent updatePaneWithController = loader.load();
            UpdateProgressController updateController = loader.getController();
            updateController.setLibrary(library);

            centerPane.getChildren().setAll(updatePaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL
    @FXML
    private void handleViewAll() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/viewAll-view.fxml"));
            Parent viewAllPaneWithController = loader.load();
            ViewAllController viewAllController = loader.getController();
            viewAllController.setLibrary(library);

            centerPane.getChildren().setAll(viewAllPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SUMMARY
    @FXML
    private void handleViewSummary() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/summary-view.fxml"));
            Parent summaryPaneWithController = loader.load();
            SummaryController summaryController = loader.getController();
            summaryController.setLibrary(library);

            centerPane.getChildren().setAll(summaryPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SETTING
    @FXML
    private void handleSettings() throws IOException {
        // fill in
    }

    // LOGOUT
    /**
     * Handles the logout action returning the view to the MediaVault login screen
     * 
     * @throws IOException if the FXML resource for the login view cannot be loaded
     */
    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}