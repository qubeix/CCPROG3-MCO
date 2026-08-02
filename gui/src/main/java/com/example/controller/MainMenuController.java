package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.Library;
import com.example.model.User;
import com.example.controller.*;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

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

    /**
     * Initalizes the main menu view
     * 
     * retrives the current user from MediaVaultController, sets the welcome label,
     * applies background styling to panels, and customizes the sidebar scrollbar.
     * 
     * @FXML This method is automaticall called after the FXML is loaded
     */
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

        if (library != null) {
            returnHome();
        }
    }

    /**
     * Applies a semi-transparent background styling to the sidebar and center
     * panels
     * 
     * sets the sidebar panel background to white with opacity. This provides a
     * subtle overlay effects which enhances readability while maintaining the
     * applications aesthetic
     */
    private void applyPanelBackgrounds() {
        sidebarBox.setBackground(new Background(
                new BackgroundFill(Color.rgb(255, 255, 255, 0.35), CornerRadii.EMPTY, Insets.EMPTY)));

        centerPane.setBackground(new Background(
                new BackgroundFill(Color.rgb(255, 255, 255, 0.55), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Applies custom styling to the sidebar scroll bar allowing for a clearer
     * looking UI
     * 
     * Sets the scroll bar in the background to transparent
     * 
     * Uses Platfrom.runLater to ensure that the scroll bar nodes are availabe after
     * the UI is rendered before applying styles
     */
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

    /**
     * Loads and displays the hoem view in the center pane
     * 
     * Uses FXMLLoader to load the home-view.fxml file and retrieves the
     * HomeController and injects the current Library into it. Replaces the center
     * pane content with the loaded home view so the user can see their libarary
     * completion progress and real-time clock
     * 
     * @FXML This method is bound to the "Home" button in the main menu
     */
    @FXML
    private void returnHome() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/home-view.fxml"));
            Parent homePaneWithController = loader.load();
            HomeController homeController = loader.getController();
            homeController.setLibrary(library);

            centerPane.getChildren().setAll(homePaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // BOOK
    /**
     * Handles the action of navigating to the "Add Book" view
     * 
     * loads the book-view.fxml file using FXMLLoader, retrieves the BookController,
     * and injects the current Library into it. Replaces the center pane content
     * with the book form so the user can add new book entries to their library
     * 
     * @FXML This method is bound to the "Add Book" button in the main menu
     */
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
    /**
     * Handles the action of navigating to the "Add Album" view
     * 
     * loads the album-view.fxml file using FXMLLoader, retrieves the
     * AlbumController,
     * and injects the current Library into it. Replaces the center pane content
     * with the album form so the user can add new album entries to their library
     * 
     * @FXML This method is bound to the "Add Album" button in the main menu
     */
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
    /**
     * Handles the action of navigating to the "Add Series" view
     * 
     * loads the series-view.fxml file using FXMLLoader, retrieves the
     * SeriesController,
     * and injects the current Library into it. Replaces the center pane content
     * with the series form so the user can add new series entries to their library
     * 
     * @FXML This method is bound to the "Add Series" button in the main menu
     */
    @FXML
    private void handleAddSeries() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/series-view.fxml"));
            Parent seriesPaneWithController = loader.load();
            SeriesController seriesController = loader.getController();
            seriesController.setLibrary(library);

            centerPane.getChildren().setAll(seriesPaneWithController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REMOVE
    /**
     * Handles the action of navigating to the "Remove Entry" view
     * 
     * Loads the removeEntry-view.fxml file using the FXMLLoader, retrives the
     * RemoveEntryController, and injects the current Library into it. Replaces the
     * center pane content with the removme entry form so the user can delete any
     * existing entry (book, album, series) from their library
     * 
     * @FXML This method is bound to the "Remove Entry" button in the main menu
     */
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
    /**
     * Handles the action of navigating to the "Rate Entry" view
     * 
     * Loadss the rate-view.fxml file using FXMLLoader, retrives the RateController,
     * and injects the current Library into it. Replaces the center pane content
     * with the rating form so the user can assign rating to existing entries (book,
     * album, series) in their library
     * 
     * @FXML This method is bound to the "Rate Entry" button in the main menu
     */
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
    /**
     * Handles the action of navigating to the "Review Entry" view
     * 
     * Loads teh code review-view.fxml file using the FXMLLoader, retrives the
     * ReviewEntryController, and injects the current Library into it. Replaces the
     * cneter pane content with the review form so the user can write or update
     * their reviews for the entries (book, album, series) in their library
     * 
     * @FXML this method is bound to the "Remove Entry" button in the main menu
     */
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
    /**
     * Handles the action of navigating to the "Update Progress" view.
     * 
     * Loads the updateProgress-view.fxml file using FXMLLoader, retreives the
     * UpdateProgressController, and injects the current Library into it. Replaces
     * the center pane contente with the update progress form so the user can modify
     * the completion status of existing entries (book, album, seties) to library
     * 
     * @FXML This method is bound to the "Update Progress" button in the main menu.
     */
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
    /**
     * Handles the action of navigating to the "View All" view.
     * 
     * Loads the viewAll-view.fxml file using FXMLLoader, retreives the
     * ViewAllController, and injects the current Library into it. Replaces
     * the center pane contente with the view-all display form so the user can
     * browse all of the existing entries (book, album, seties) to library
     * 
     * @FXML This method is bound to the "View Alls" button in the main menu.
     */
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
    /**
     * Handles the action of navigating to the "View Summary" view.
     * 
     * Loads the summary-view.fxml file using FXMLLoader, retreives the
     * SummaryController, and injects the current Library into it. Replaces
     * the center pane contente with the summary display form so the user can modify
     * the completion status of existing entries (book, album, seties) to library
     */
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
    /**
     * Handles the action of navigating to the "Settings" view.
     * 
     * loads teh setting-view.fxml file using FXMLLoader, retrieves the
     * SettingsController, and injects the User into it.
     * 
     * Replaces the center pane content with the settings form so the user can
     * update their account preferences and application configurations
     * 
     * @FXML This method is bound to the "Settings" button in the main menu.
     */
    @FXML
    private void handleSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/settings-view.fxml"));
            Parent settingsPaneWithController = loader.load();
            SettingsController settingsController = loader.getController();
            settingsController.setCurrentUser(currentUser);

            centerPane.getChildren().setAll(settingsPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
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