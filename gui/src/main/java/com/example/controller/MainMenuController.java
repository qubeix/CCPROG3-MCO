package com.example.controller;

import java.io.IOException;

import com.example.MediaVault;
import com.example.model.Library;
import com.example.model.NumberTextField;
import com.example.model.User;

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

    @FXML
    private VBox removeEntryPanel;

    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

    //book
    // @FXML
    // private VBox addBookPanel;

    // @FXML 
    // private TextField bookTitleField;
    
    // @FXML 
    // private TextField bookAuthorField;
    
    // @FXML 
    // private ToggleGroup bookGenreGroup;

    // @FXML
    // private RadioButton romanceButton;

    // @FXML
    // private RadioButton mysteryButton;

    // @FXML
    // private RadioButton fantasyButton;

    // @FXML
    // private RadioButton nonfictionButton;

    // @FXML
    // private RadioButton selfhelpButton;

    // @FXML
    // private NumberTextField bookChapter;

    // @FXML 
    // private Label bookErrorLabel;

    //album
    // @FXML
    // private VBox addAlbumPanel;

    // @FXML 
    // private TextField albumTitleField;
    
    // @FXML 
    // private TextField albumArtistField;
    
    // @FXML 
    // private ToggleGroup albumGenreGroup;

    // @FXML
    // private RadioButton popButton;

    // @FXML
    // private RadioButton hipHopButton;

    // @FXML
    // private RadioButton rockButton;

    // @FXML
    // private RadioButton jazzButton;

    // @FXML
    // private RadioButton emdButton;

    // @FXML
    // private NumberTextField albumTracks;

    // @FXML 
    // private Label albumErrorLabel;

    //series
    // @FXML
    // private VBox addSeriesPanel;
    
    // @FXML 
    // private TextField albumArtistField;
    
    // @FXML 
    // private ToggleGroup seriesGenreGroup;

    // @FXML
    // private RadioButton popButton;

    // @FXML
    // private RadioButton hipHopButton;

    // @FXML
    // private RadioButton rockButton;

    // @FXML
    // private RadioButton jazzButton;

    // @FXML
    // private RadioButton emdButton;

    // @FXML
    // private NumberTextField albumTracks;

    // @FXML 
    // private Label albumErrorLabel;

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

    private void hideAllPanels(){       //this allows for the panels to hide after being clicked to avoid overlapping
        outputArea.setVisible(false);
        outputArea.setManaged(false);

        //book
        addBookPanel.setVisible(false);
        addBookPanel.setManaged(false);


        //album
        addAlbumPanel.setVisible(false);
        addAlbumPanel.setManaged(false);

        //series
        // addSeriesPanel.setVisible(false);
        // addSeriesPanel.setManaged(false);


        //remove 
        removeEntryPanel.setVisible(false);
        removeEntryPanel.setManaged(false);
    }

    @FXML
    private void returnHome() {
        // fill in
    }

/*==========================================================================================================
                                        B O O K
===========================================================================================================*/
    @FXML
    private void handleAddBook(){
        try {
            Parent bookPane = MediaVault.loadFXML("book-view");

            //FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/book-panel.fxml"));
            FXMLLoader loader = new FXMLLoader(MediaVault.class.getResource("/com/example/view/book-view.fxml"));
            Parent bookPaneWithController = loader.load();
            BookController bookController = loader.getController();
            bookController.setLibrary(library);

            centerPane.getChildren().setAll(bookPaneWithController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @FXML
    // private void handleAddBook() throws IOException {
    //     bookTitleField.clear();
    //     bookAuthorField.clear();
    //     bookGenreGroup.selectToggle(null);
    //     bookChapter.clear();
    //     bookErrorLabel.setVisible(false);

    //     hideAllPanels();
    //     addBookPanel.setVisible(true);
    //     addBookPanel.setManaged(true);
    // }

    // @FXML
    // private void confirmAddBook(){
    //     String title = bookTitleField.getText();
    //     String author = bookAuthorField.getText();
    //     String chapterText = bookChapter.getText();
    //     RadioButton selectedGenre = (RadioButton) bookGenreGroup.getSelectedToggle();

    //     if(title.isEmpty() || author.isEmpty() || selectedGenre == null || chapterText.isEmpty()){
    //         bookErrorLabel.setText("Please fill in all fields before submitting");
    //         bookErrorLabel.setVisible(true);
    //     }
    //     else{
    //         boolean isValidNumber = true;
    //         int chapterCount = 0;

    //         try {
    //             chapterCount = Integer.parseInt(chapterText);
    //         } catch (NumberFormatException e) {
    //             isValidNumber = false;
    //         }

    //         if(!isValidNumber || chapterCount <= 0){
    //             bookErrorLabel.setText("Please enter a valid postive number for the chapter.");
    //             bookErrorLabel.setVisible(true);
    //         }
    //         else{
    //             String genre = selectedGenre.getText();
    //             library.addBookInput(title, author, genre, chapterCount);
    //             bookErrorLabel.setVisible(false);

    //             addBookPanel.setVisible(false);
    //             addBookPanel.setManaged(false);
    //             outputArea.setVisible(true);
    //             outputArea.setManaged(true);
    //             outputArea.setText("\"" + title + "\" by " + author + " added to your Book Library.");
    //         }
    //     }

    // }

    // @FXML
    // private void clearAddBook() {
    //     bookTitleField.clear();
    //     bookAuthorField.clear();
    //     bookGenreGroup.selectToggle(null);
    //     bookChapter.clear();
    //     bookErrorLabel.setVisible(false);
    // }

/*==========================================================================================================
                                        A L B U M 
===========================================================================================================*/
    @FXML
    private void handleAddAlbum(){
        albumTitleField.clear();
        albumArtistField.clear();
        albumGenreGroup.selectToggle(null);
        albumTracks.clear();
        albumErrorLabel.setVisible(false);

        // outputArea.setVisible(false);
        // outputArea.setManaged(false);
        hideAllPanels();
        addAlbumPanel.setVisible(true);
        addAlbumPanel.setManaged(true);
    }

    @FXML
    private void confirmAddAlbum() {
        String title = albumTitleField.getText();
        String artist = albumArtistField.getText();
        String tracksText = albumTracks.getText();
        RadioButton selectedGenre = (RadioButton) albumGenreGroup.getSelectedToggle();

        if (title.isEmpty() || artist.isEmpty() || selectedGenre == null || tracksText.isEmpty()) {
            albumErrorLabel.setText("Please fill in all fields before submitting");
            albumErrorLabel.setVisible(true);
        } 
        else {
            boolean isValidNumber = true;
            int trackCount = 0;
            try {
                trackCount = Integer.parseInt(tracksText);
            } 
            catch (NumberFormatException e) {
                isValidNumber = false;
            }

            if (!isValidNumber || trackCount <= 0) {
                albumErrorLabel.setText("Please enter a valid positive number for tracks.");
                albumErrorLabel.setVisible(true);
            } 
            else {
                String genre = selectedGenre.getText();
                library.addAlbumInput(title, artist, genre, trackCount);
                albumErrorLabel.setVisible(false);

                addAlbumPanel.setVisible(false);
                addAlbumPanel.setManaged(false);
                outputArea.setVisible(true);
                outputArea.setManaged(true);
                //cancelAddAlbum();
                outputArea.setText("\"" + title + "\" by " + artist + " added to your Album Library.");

            }
        }
    }

    @FXML
    private void clearAddAlbum() {
        albumTitleField.clear();
        albumArtistField.clear();
        albumGenreGroup.selectToggle(null);
        albumTracks.clear();
        albumErrorLabel.setVisible(false);

        // addAlbumPanel.setVisible(false);
        // addAlbumPanel.setManaged(false);
        //hideAllPanels();
        // outputArea.setVisible(true);
        // outputArea.setManaged(true);
    }

/*==========================================================================================================
                                        S E R I E S
===========================================================================================================*/

    @FXML
    private void handleAddSeries() {
        // fill in
    }

    @FXML
    private void continueAddSeries(){

    }

/*==========================================================================================================
                            R E M O V E  E N T R I E S
===========================================================================================================*/

    @FXML
    private void handleRemoveEntry() {
        removeTypeCombo.getItems().setAll("Book", "Album", "Series");
        removeTypeCombo.setValue(null);
        removeEntryCombo.getItems().clear();
        
        // outputArea.setVisible(false);
        // outputArea.setManaged(false);
        hideAllPanels();
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
        } 
        else if ("Album".equals(type)) {
            for (int i = 0; i < library.getAlbumCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
            }
        } 
        else if ("Series".equals(type)) {
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
        } 
        else if ("Album".equals(type)) {
            library.removeAlbumAt(index);
        } 
        else if ("Series".equals(type)) {
            library.removeSeriesAt(index);
        }

        outputArea.setText("Entry removed.");
        clearRemoveEntry();
    }

    @FXML
    private void clearRemoveEntry() {
        removeEntryPanel.setVisible(false);
        removeEntryPanel.setManaged(false);

        outputArea.setVisible(true);
        outputArea.setManaged(true);
    }

/*==========================================================================================================
                            R A T E  E N T R I E S 
===========================================================================================================*/

    @FXML
    private void handleRateEntry() {
        // fill in
    }

/*==========================================================================================================
                        R E V I E W  E N T R I E S
===========================================================================================================*/

    @FXML
    private void handleReviewEntry() {
        // fill in
    }

/*==========================================================================================================
                U P D A T E  P R O G R E S S
===========================================================================================================*/

    @FXML
    private void handleUpdateProgress() {
        // fill in
    }

/*==========================================================================================================
                    V I E W  A L L
===========================================================================================================*/

    @FXML
    private void handleViewAll() throws IOException {
        outputArea.setText(library.getAllEntriesText());
    }

/*==========================================================================================================
                        S U M M A R Y
===========================================================================================================*/

    @FXML
    private void handleViewSummary() throws IOException {
        outputArea.setText(library.getSummaryText());
    }

/*==========================================================================================================
                        S E T T I N G S
===========================================================================================================*/

    @FXML
    private void handleSettings() throws IOException{
        //fill in 
    }

/*==========================================================================================================
                        L O G O U T
===========================================================================================================*/

    @FXML
    private void handleLogout() throws IOException {
        MediaVault.setRoot("mediavault-view");
    }
}