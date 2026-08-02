package com.example.controller;

import com.example.model.Album;
import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AlbumController {

    @FXML
    private TextField albumTitleField;

    @FXML
    private TextField albumArtistField;

    @FXML
    private ToggleGroup albumGenreGroup;

    @FXML
    private NumberTextField albumTracks;

    @FXML
    private Label albumErrorLabel;

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
     * 
     * @param title         the title of the album entered by the user
     * @param artist        the artist of the album entered by the user
     * @param trackText     the number of tracks in the album provided by the user
     * @param selectedGenre the selected genre from teh toggle group
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
