package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Controller class for handling reviews of library entries.
 * Provides methods to select completed entries and add user-written reviews.
 */
public class ReviewEntryController {

    /** Combo box for selecting the type of entry to review (Book, Album, Series). */
    @FXML
    private ComboBox<String> reviewTypeCombo;

    /** Combo box for selecting a specific entry of the chosen type. */
    @FXML
    private ComboBox<String> reviewEntryCombo;

    /** Text area for entering the review text. */
    @FXML
    private TextArea reviewTextArea;

    /** Label for displaying success or error messages related to reviews. */
    @FXML
    private Label reviewEntryMessage;

    /** Reference to the user's library. */
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
     * Set up choices in the reviewTypeCombo with the options (Book, Album, Series),
     * and hides the reviewEntryMessage by deafult until it is needed
     */
    @FXML
    private void initialize() {
        reviewTypeCombo.getItems().setAll("Book", "Album", "Series");
        reviewEntryMessage.setVisible(false);
    }

    /**
     * Handles the selection of a type for review
     * 
     * clears the reviewEntryCombo items and fills them based on the selected type
     * (book, album, series). Only entries with a status of "Completed" are listed
     * with their index and title, allowing the user to choose which entry to review
     */
    @FXML
    private void onReviewTypeSelected() {
        String type = reviewTypeCombo.getValue();
        reviewEntryCombo.getItems().clear();

        if (type != null) {
            switch (type) {
                case "Book":
                    for (int i = 0; i < library.getBookCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getBookEntry(i).getStatus())) {
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                        }
                    }
                    break;

                case "Album":
                    for (int i = 0; i < library.getAlbumCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getAlbumEntry(i).getStatus())) {
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                        }

                    }
                    break;

                case "Series":
                    for (int i = 0; i < library.getSeriesCount(); i++) {
                        if ("Completed".equalsIgnoreCase(library.getSeriesEntry(i).getStatus())) {
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                        }

                    }
            }
        }
    }

    /**
     * Confirms and adds a review to the selected entry
     * 
     * Retrieves the selected type (book, album, series), the chosesn entry, and the
     * review text entered by the user. If all inputs are valid, the review is added
     * to the corresponding entry in the library and a success message is displayed.
     * If any input is missing, an error message is shown instead
     */
    @FXML
    private void confirmReviewEntry() {
        String type = reviewTypeCombo.getValue();
        String selected = reviewEntryCombo.getValue();
        String review = null;

        if (!reviewTextArea.getText().trim().isEmpty()) {
            review = reviewTextArea.getText();
        }

        if (type != null && selected != null && review != null) {
            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;

            if ("Book".equals(type)) {
                library.getBookEntry(index).addReview(review);
            } else if ("Album".equals(type)) {
                library.getAlbumEntry(index).addReview(review);
            } else if ("Series".equals(type)) {
                library.getSeriesEntry(index).addReview(review);
            }

            reviewEntryMessage.setStyle("-fx-text-fill: GREEN");
            reviewEntryMessage.setText("Entry reviewed.");
            reviewEntryMessage.setVisible(true);

            clearReviewEntry();
        } else {
            reviewEntryMessage.setStyle("-fx-text-fill: RED");
            reviewEntryMessage.setText("Please fill all areas.");
            reviewEntryMessage.setVisible(true);
        }
    }

    /**
     * clears the current review entry form
     * 
     * resets the reviewTypeCombo and removeEntryCombo selection to null and clears
     * the removeEntryCombo field, and clears the reviewTextArea field, preparing
     * the form for a new review
     */
    @FXML
    private void clearReviewEntry() {
        reviewTypeCombo.setValue(null);
        reviewEntryCombo.getItems().clear();
        reviewTextArea.clear();
    }
}