package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.Library;
import com.example.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
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

    @FXML
    private VBox removeEntryPanel;

    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

    @FXML
    private VBox addAlbumPanel;

    @FXML 
    private TextField albumTitleField;
    
    @FXML 
    private TextField albumArtistField;
    
    @FXML 
    private ToggleGroup albumGenreGroup;

    @FXML
    private RadioButton popButton;

    @FXML
    private RadioButton hipHopButton;

    @FXML
    private RadioButton rockButton;

    @FXML
    private RadioButton jazzButton;

    @FXML
    private RadioButton endButton;

    @FXML
    private TextField albumTrackField;

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

    //ALBUM
    @FXML
    private void handleAddAlbum(){
        albumTitleField.clear();
        albumArtistField.clear();
        albumGenreGroup.selectToggle(null);
        albumTracksField.clear();

        outputArea.setVisible(false);
        outputArea.setManaged(false);
        addAlbumPanel.setVisible(true);
        addAlbumPanel.setManaged(true);
    }

    @FXML
    private void confirmAddAlbum() {
        String title = albumTitleField.getText();
        String artist = albumArtistField.getText();
        String tracksText = albumTracksField.getText();
        RadioButton selectedGenre = (RadioButton) albumGenreGroup.getSelectedToggle();

        if (title.isEmpty() || artist.isEmpty() || selectedGenre == null || tracksText.isEmpty()) {
            cancelAddAlbum();
            outputArea.setText("Please fill in all fields before submitting.");
        } else {
            boolean isValidNumber = true;
            int trackCount = 0;
            try {
                trackCount = Integer.parseInt(tracksText);
            } catch (NumberFormatException e) {
                isValidNumber = false;
            }

            if (!isValidNumber || trackCount <= 0) {
                cancelAddAlbum();
                outputArea.setText("Please enter a valid positive number for tracks.");
            } else {
                String genre = selectedGenre.getText();
                library.addAlbumInput(title, artist, genre, trackCount);
                cancelAddAlbum();
                outputArea.setText("\"" + title + "\" by " + artist + " added to your Album Library.");
            }
        }
    }

    @FXML
    private void cancelAddAlbum() {
        addAlbumPanel.setVisible(false);
        addAlbumPanel.setManaged(false);
        outputArea.setVisible(true);
        outputArea.setManaged(true);
    }

    @FXML
    private void handleAddSeries() {
        // fill in
    }

    @FXML
    private void handleRemoveEntry() {
        removeTypeCombo.getItems().setAll("Book", "Album", "Series");
        removeTypeCombo.setValue(null);
        removeEntryCombo.getItems().clear();
        
        outputArea.setVisible(false);
        outputArea.setManaged(false);
        removeEntryPanel.setVisible(true);
        removeEntryPanel.setManaged(true);
    }

    @FXML
    private void onRemoveTypeSelected(){
        String type = removeTypeCombo.getValue();
        removeEntryCombo.getItems().clear();

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
            outputArea.setText("Please select both a type and an entry to remove.");
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
        removeEntryPanel.setVisible(false);
        removeEntryPanel.setManaged(false);

        outputArea.setVisible(true);
        outputArea.setManaged(true);
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
    private void handleSettings() throws IOException{
        //fill in 
    }

    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}