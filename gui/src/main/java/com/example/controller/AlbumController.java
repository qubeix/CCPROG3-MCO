package com.example.controller;

import com.example.model.Album;
import com.example.model.Library;
import com.example.model.NumberTextField;
import com.example.model.User;

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

    private User currentUser;
    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void confirmAddAlbum() {
        String title = albumTitleField.getText();
        String artist = albumArtistField.getText();
        String trackText = albumTracks.getText();
        RadioButton selectedGenre = (RadioButton) albumGenreGroup.getSelectedToggle();

        if (title.isEmpty() || artist.isEmpty() || selectedGenre == null || trackText.isEmpty()) {
            albumErrorLabel.setStyle("-fx-text-fil: RED");
            albumErrorLabel.setText("Please fill in all fields before submitting");
            albumErrorLabel.setVisible(true);

        } else {
            try {
                int trackCount = Integer.parseInt(trackText);
                if (trackCount <= 0)
                    throw new NumberFormatException();

                Album newAlbum = library.addAlbumInput(title, artist, selectedGenre.getText(), trackCount);

                albumErrorLabel.setStyle("=fx-text-fill: GREEN");
                albumErrorLabel.setText("Added: " + newAlbum.toString());
                albumErrorLabel.setVisible(true);

            } catch (NumberFormatException e) {
                albumErrorLabel.setStyle("-fx-text-fill: RED");
                albumErrorLabel.setText("Please enter a valid positive number for tracks.");
                albumErrorLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void clearAddAlbum() {
        albumTitleField.clear();
        albumArtistField.clear();
        albumGenreGroup.selectToggle(null);
        albumTracks.clear();
    }
}
