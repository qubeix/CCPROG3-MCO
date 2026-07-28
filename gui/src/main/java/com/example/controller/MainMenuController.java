package com.example.controller;

// import com.example.model.Library;
// import com.example.model.User;

import java.io.IOException;

import com.example.model.*;
import com.example.controller.*;
import com.example.MediaVault;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.*;

// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextArea;

public class MainMenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private StackPane stackPaneArea;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button addBookButton;

    @FXML
    private Button addAlbumButton;

    @FXML
    private Button addSeriesButton;

    @FXML
    private Label text;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField bookAuthor;

    @FXML
    private ToggleGroup genreGroup;

    @FXML
    private RadioButton romanceButton;

    @FXML
    private RadioButton mysteryButton;

    @FXML
    private RadioButton fantasyButton;

    @FXML
    private RadioButton nonfictionButton;

    @FXML
    private RadioButton selfhelpButton;

    @FXML
    private TextField bookChapters;

    @FXML
    private Button submitBookButton;

    @FXML
    private Button removeEntryButton;

    @FXML
    private VBox removeEntryOverlay;

    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

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
        AddBookController addBookView = new AddBookController();
        stackPaneArea.getChildren().setAll(addBookView);
    }

    @FXML
    private void handleAddAlbum() throws IOException {
        // MediaVault.setRoot("addalbum-view");

    }

    @FXML
    private void handleAddSeries() {
        // fill in
    }

    // remove entries
    @FXML
    private void handleRemoveEntry() {
        // removeTypeCombo.getItems().setAll("Book", "Album", "Series");
        // removeTypeCombo.setValue(null);
        // removeEntryCombo.getItems().clear();
        removeEntryOverlay.setVisible(true);
        removeEntryOverlay.setManaged(true);

    }

    @FXML
    private void onTypeSelected() {
        String type = removeTypeCombo.getValue();
        removeEntryCombo.getItems().clear();

        // book
        if ("Book".equals(type)) {
            for (int i = 0; i < library.getBookCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
            }
        } else if ("Album".equals(type)) {
            for (int i = 0; i < library.getAlbumCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
            }
        } else if ("Series".equals(type)) {
            for (int i = 0; i < library.getSeriesCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
            }
        }
    }

    @FXML
    private void confirmRemoveEntry() {
        String type = removeTypeCombo.getValue();
        String selected = removeEntryCombo.getValue();

        if (type == null || selected == null) {
            outputArea.setText("Please selected both a type and an entry to remove.");
        }

        int index = Integer.parseInt(selected.split("\\.")[0]) - 1;

        if ("Book".equals(type)) {
            library.removeBookAt(index);
        } else if ("Album".equals(type)) {
            library.removeAlbumAt(index);
        } else if ("Series".equals(type)) {
            library.removeSeriesAt(index);
        }

        outputArea.setText("Entry removed.");
        cancelRemoveEntry();
    }

    @FXML
    private void cancelRemoveEntry() {
        removeEntryOverlay.setVisible(false);
        removeEntryOverlay.setManaged(false);
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

    // view all entries
    @FXML
    private void handleViewAll() throws IOException {
        // Text entries = new Text(library.getAllEntriesText());
        // stackPaneArea.getChildren().add(entries);

        outputArea.setText(library.getAllEntriesText());
    }

    // view summary
    @FXML
    private void handleViewSummary() throws IOException {
        // Text summary = new Text(library.getSummaryText());
        // stackPaneArea.getChildren().add(summary);

        outputArea.setText(library.getSummaryText());
    }

    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}
