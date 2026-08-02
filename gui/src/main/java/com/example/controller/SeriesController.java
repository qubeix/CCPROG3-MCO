package com.example.controller;

import com.example.model.Series;
import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

public class SeriesController {

    @FXML
    private TextField seriesTitleField;

    @FXML
    private NumberTextField seriesSeasons;

    @FXML
    private NumberTextField seriesEpisodes;

    @FXML
    private ToggleGroup seriesGenreGroup;

    @FXML
    private Label seriesErrorLabel;

    private Library library;

    private int[] episodeCount;

    private int episodesAdded = 0;

    private int seasonCount = -1;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void setAddSeasons() {
        try {
            if (Integer.parseInt(seriesSeasons.getText()) <= 0)
                throw new NumberFormatException();

            else {
                seasonCount = Integer.parseInt(seriesSeasons.getText()); // get the season count

                episodeCount = new int[seasonCount]; // initialize the array of episode count accoirding to the season
                                                     // count

                int i;
                for (i = 0; i < seasonCount; i++) // set all episode count to -1, to check later if all seasons have
                                                  // been assigned a valid episode count
                {
                    episodeCount[i] = -1;
                }

                seriesEpisodes.setPromptText("No. of Episodes for S" + (episodesAdded + 1)); // set the episodes
                                                                                             // text field to
                                                                                             // get for the
                                                                                             // first season
                seriesEpisodes.setDisable(false);
            }

        } catch (NumberFormatException e) {
            seriesErrorLabel.setStyle("-fx-text-fill: RED");
            seriesErrorLabel.setText("Please enter a valid positive number for seasons.");
            seriesErrorLabel.setVisible(true);
        }
    }

    @FXML
    private void setAddEpisodes() {
        int episodeCountIndiv = Integer.parseInt(seriesEpisodes.getText());

        try {
            if (episodeCountIndiv <= 0 && episodesAdded != seasonCount) {
                throw new NumberFormatException();
            } else {
                episodeCount[episodesAdded] = episodeCountIndiv;

                episodesAdded++;

                seriesEpisodes.clear();

                if (episodesAdded < seasonCount)
                    seriesEpisodes.setPromptText("No. of Episodes for S" + (episodesAdded + 1));
                else {
                    seriesEpisodes.setPromptText("All Episodes Added");
                    seriesEpisodes.setDisable(true);
                }
            }
        } catch (NumberFormatException e) {
            seriesErrorLabel.setStyle("-fx-text-fill: RED");
            seriesErrorLabel.setText("Please enter a valid positive number for episodes.");
            seriesErrorLabel.setVisible(true);
        }
    }

    @FXML
    private void confirmAddSeries() {
        String title = seriesTitleField.getText();

        RadioButton selectedGenre = (RadioButton) seriesGenreGroup.getSelectedToggle();

        boolean invalidEpisodeCount = false; // check if all seasons have been assigned a valid episode count
        int i;
        for (i = 0; i < seasonCount; i++) {
            if (episodeCount[i] == -1)
                invalidEpisodeCount = true;
        }

        if (title.isEmpty() || selectedGenre == null || seasonCount == -1 || invalidEpisodeCount) { // add condition to
                                                                                                    // check if
                                                                                                    // allepisodes are
                                                                                                    // not empty
            seriesErrorLabel.setStyle("-fx-text-fill: RED");
            seriesErrorLabel.setText("Please fill in all fields before submitting");
            seriesErrorLabel.setVisible(true);

        } else {
            try {
                Series newSeries = library.addSeriesInput(title, selectedGenre.getText(), seasonCount, episodeCount);

                seriesErrorLabel.setStyle("-fx-text-fill: GREEN");
                seriesErrorLabel.setText("Added: " + newSeries.toString());
                seriesErrorLabel.setVisible(true);
                episodesAdded = 0;
                seasonCount = -1;
            } catch (NullPointerException e) {
                seriesErrorLabel.setStyle("-fx-text-fill: RED");
                seriesErrorLabel.setText("Please complete episode counts.");
                seriesErrorLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void clearAddSeries() {
        seriesTitleField.clear();
        seriesGenreGroup.selectToggle(null);
        seriesSeasons.clear();
        seriesEpisodes.clear();
    }
}
