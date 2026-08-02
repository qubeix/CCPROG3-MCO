package com.example.controller;

import com.example.model.Library;
import com.example.model.NumberTextField;
import com.example.model.Series;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class for handling series-related input in the GUI.
 * Provides methods to add new series entries with season and episode details.
 */
public class SeriesController {

    /** Text field for entering the series title. */
    @FXML
    private TextField seriesTitleField;

    /** Numeric text field for entering the number of seasons. */
    @FXML
    private NumberTextField seriesSeasons;

    /** Numeric text field for entering the number of episodes per season. */
    @FXML
    private NumberTextField seriesEpisodes;

    /** Toggle group for selecting the series genre. */
    @FXML
    private ToggleGroup seriesGenreGroup;

    /** Label for displaying success or error messages related to series input. */
    @FXML
    private Label seriesErrorLabel;

    /** Reference to the user's library. */
    private Library library;

    /** Array storing the episode count for each season. */
    private int[] episodeCount;

    /** Counter tracking how many seasons have had episodes assigned. */
    private int episodesAdded = 0;

    /** Total number of seasons, initialized to -1 until set. */
    private int seasonCount = -1;

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
     * sets the number of seasons for a series
     * 
     * validates the input from the seriesSeason field to ensure it is a poitive
     * number. If it is valid, it initalizes the episodeCount array based on the
     * season count and sets all values to -1 to indicate that is has not yet been
     * assigned. Also updates the seariesEpisodes field prompt to request the number
     * of episodes for the first season and enables the input field
     * if input is invalid, it dispalys an error message
     */
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

    /**
     * Sets the number of episodes for each season of a series
     * 
     * Validates the input from the seriesEpisodes field to ensure it is a positive
     * number. If valid, assigns the episode count to the current season index and
     * increments the episodesAdded counter. Clears the input field and updates the
     * prompt for the next season until all seasons have been assigned.
     * Once completed, disables the input field and shows a confirmation prompt. If
     * the input is invalid, an error message is displayed
     */
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

    /**
     * Confirms and adds a new series entry to the library
     * 
     * validates the input fields including the series title, selected genre, season
     * count, and episode counts. If all inputs are valid, creates a new series
     * object and adds it to the library. Displays a success message with the added
     * series details. If any input is missing or invalid, an error message is shown
     * instead. Resets episodesAdd and seasonCount after a successful addition
     */
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

    /**
     * clears the series addition form
     * 
     * Resets all input field related the adding series, including the title, genre
     * selection, season count, and episode count. Prepares the form for entering a
     * new series
     */
    @FXML
    private void clearAddSeries() {
        seriesTitleField.clear();
        seriesGenreGroup.selectToggle(null);
        seriesSeasons.clear();
        seriesEpisodes.clear();
    }
}
