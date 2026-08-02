package com.example.controller;

import com.example.model.Album;
import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class for handling album-related input in the GUI.
 * Provides methods to add albums to the library and reset input fields.
 */
public class AlbumController {

     /**Text field for entering the album title. */
    @FXML
    private TextField albumTitleField;

    /** Text field for entering the album artist. */
    @FXML
    private TextField albumArtistField;

    /** Toggle group for selecting the album genre. */
    @FXML
    private ToggleGroup albumGenreGroup;

    /** Custom text field for entering the number of tracks (numeric only). */
    @FXML
    private NumberTextField albumTracks;

    /** Label for displaying error or success messages. */
    @FXML
    private Label albumErrorLabel;

    /** Reference to the user's library for storing albums. */
    private Library library;

    /**
     * This method is the parent controller after loading the FXML,
     * allowing the controller to access the user's library data
     * 
     * @param library this object is associated with this controller
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * Accepts a title, artist, genre, and track count
     * if the fileds is empty or invalid it dispals an error message in red
     * if all iputs are valid, initializes a new object and add its to the library,
     * displaying a sucessfully added message in green
     */
    @FXML
    private void confirmAddAlbum() {
        String title = albumTitleField.getText();
        String artist = albumArtistField.getText();
        String trackText = albumTracks.getText();
        RadioButton selectedGenre = (RadioButton) albumGenreGroup.getSelectedToggle();

        if (title.isEmpty() || artist.isEmpty() || selectedGenre == null || trackText.isEmpty()) {
            albumErrorLabel.setStyle("-fx-text-fill: RED");
            albumErrorLabel.setText("Please fill in all fields before submitting");
            albumErrorLabel.setVisible(true);

        } else {
            try {
                int trackCount = Integer.parseInt(trackText);
                if (trackCount <= 0)
                    throw new NumberFormatException();

                Album newAlbum = library.addAlbumInput(title, artist, selectedGenre.getText(), trackCount);

                albumErrorLabel.setStyle("-fx-text-fill: GREEN");
                albumErrorLabel.setText("Added: " + newAlbum.toString());
                albumErrorLabel.setVisible(true);

            } catch (NumberFormatException e) {
                albumErrorLabel.setStyle("-fx-text-fill: RED");
                albumErrorLabel.setText("Please enter a valid positive number for tracks.");
                albumErrorLabel.setVisible(true);
            }
        }
    }

    /**
     * Resets the album title, artist, genre selection, and track count fields
     * to their default empty.
     * This is triggered when the user clocks the "Clear" button. allowing the user
     * to start fresh without any previously entered valeus
     */
    @FXML
    private void clearAddAlbum() {
        albumTitleField.clear();
        albumArtistField.clear();
        albumGenreGroup.selectToggle(null);
        albumTracks.clear();
    }
}
