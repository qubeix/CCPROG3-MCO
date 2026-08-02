package com.example.controller;

import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RateController {

    @FXML
    private ComboBox<String> rateTypeCombo;

    @FXML
    private ComboBox<String> rateEntryCombo;

    @FXML
    private NumberTextField inputRating;

    @FXML
    private Label rateEntryMessage;

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
     * Initializes the controller after its root element has been completely loaded.
     * 
     * Set up choices in the rateTypeCombo with the options (Book, Album, Series),
     * and hides the rateEntryMessage by deafult until it is needed
     * 
     * @FXML this method is automatically called after the FXML components have been
     *       loaded
     */
    @FXML
    private void initialize() {
        rateTypeCombo.getItems().setAll("Book", "Album", "Series");
        rateEntryMessage.setVisible(false);
    }

    /**
     * Handles the selection of a rate type from the combo box
     * 
     * Clears the rateEntryCombo items and sets them based on the selected entry
     * type (book, album, series). Only the entries with a status of "Completed" are
     * added to the list. allowing the user to choose which completed item to rate
     * 
     * @FXML the method is triggered once the user selects a type from the
     *       rateTypeCombo
     */

    @FXML
    private void onRateTypeSelected() {
        String type = rateTypeCombo.getValue();
        rateEntryCombo.getItems().clear();

        if (type != null) {
            switch (type) {
                case "Book":
                    for (int i = 0; i < library.getBookCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getBookEntry(i).getStatus())) {
                            rateEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                        }
                    }
                    break;

                case "Album":
                    for (int i = 0; i < library.getAlbumCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getAlbumEntry(i).getStatus())) {
                            rateEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                        }
                    }
                    break;

                case "Series":
                    for (int i = 0; i < library.getSeriesCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getSeriesEntry(i).getStatus())) {
                            rateEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                        }
                    }
            }
        }
    }

    /**
     * Confirms and applies a rating to the selected entry
     * 
     * retrives the selected entry type (book, album, series), and allows the user
     * to rate the entry. If all inputs are valid and the rating is between 1-10,
     * the rating is added to the entry in the library. Displays a success message
     * when the raiting is applied, or an error message if the input is invalid or
     * incomplete
     * 
     * @FXML this method is triggered when the usesr clicks the confirm button when
     *       rating an entry
     */
    @FXML
    private void confirmRateEntry() {
        String type = rateTypeCombo.getValue();
        String selected = rateEntryCombo.getValue();
        int rating = 0;
        if (!inputRating.getText().trim().isEmpty()) {
            rating = Integer.parseInt(inputRating.getText());
        }

        if (type != null && selected != null && rating != 0) {
            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;

            if (rating > 0 && rating <= 10) {
                if ("Book".equals(type)) {
                    library.getBookEntry(index).addRating(rating);
                    // library.rateBookAt(index, rating);
                } else if ("Album".equals(type)) {
                    library.getAlbumEntry(index).addRating(rating);
                    // library.rateAlbumAt(index, rating);
                } else if ("Series".equals(type)) {
                    library.getSeriesEntry(index).addRating(rating);
                    // library.rateSeriesAt(index, rating);
                }

                rateEntryMessage.setStyle("-fx-text-fill: GREEN");
                rateEntryMessage.setText("Entry rated.");
                rateEntryMessage.setVisible(true);

                clearRateEntry();

            } else {
                rateEntryMessage.setStyle("-fx-text-fill: RED");
                rateEntryMessage.setText("Invalid rating. Please choose a number from 1-10.");
                rateEntryMessage.setVisible(true);
            }
        } else {
            rateEntryMessage.setStyle("-fx-text-fill: RED");
            rateEntryMessage.setText("Please fill all areas.");
            rateEntryMessage.setVisible(true);
        }
    }

    /**
     * clears the current rating entry form
     * 
     * resets the rateTypeCombo and rateEntryCombo selection to null and clears the
     * inputRating field, preparing the form for a new entry
     * 
     * @FXML this method is triggered when the user clicks the clear button
     */
    @FXML
    private void clearRateEntry() {
        rateTypeCombo.setValue(null);
        rateEntryCombo.setValue(null);
        inputRating.clear();
    }
}
