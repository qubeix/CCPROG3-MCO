package com.example.controller;

import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
// import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class RateController {

    @FXML
    private VBox rateEntryPanel;

    @FXML
    private ComboBox<String> rateTypeCombo;

    @FXML
    private ComboBox<String> rateEntryCombo;

    @FXML
    private NumberTextField inputRating;

    @FXML
    private Label rateEntryMessage;

    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void initialize() {
        rateTypeCombo.getItems().setAll("Book", "Album", "Series");
        rateEntryMessage.setVisible(false);
    }

    /**
     * Initializes the entry options depending on the selected media type
     */
    @FXML
    private void onRateTypeSelected() {
        String type = rateTypeCombo.getValue();
        rateEntryCombo.getItems().clear();

        if (type != null) {
            switch (type) {
                case "Book":
                    for (int i = 0; i < library.getBookCount(); i++) {
                        rateEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                    }
                    break;

                case "Album":
                    for (int i = 0; i < library.getAlbumCount(); i++) {
                        rateEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                    }
                    break;

                case "Series":
                    for (int i = 0; i < library.getSeriesCount(); i++) {
                        rateEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                    }
            }
        }
    }

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

            if ("Book".equals(type)) {
                library.rateBookAt(index, rating);
            } else if ("Album".equals(type)) {
                library.rateAlbumAt(index, rating);
            } else if ("Series".equals(type)) {
                library.rateSeriesAt(index, rating);
            }

            rateEntryMessage.setStyle("-fx-text-fill: GREEN");
            rateEntryMessage.setText("Entry rated.");
            rateEntryMessage.setVisible(true);

            clearRateEntry();
        } else {
            rateEntryMessage.setStyle("-fx-text-fill: RED");
            rateEntryMessage.setText("Please select both a type and an entry to remove, and add a rating.");
            rateEntryMessage.setVisible(true);
        }
    }

    @FXML
    private void clearRateEntry() {
        rateTypeCombo.setValue(null);
        rateEntryCombo.setValue(null);
        inputRating.clear();
    }
}
