package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ReviewEntryController {

    @FXML
    private VBox reviewEntryPanel;

    @FXML
    private ComboBox<String> reviewTypeCombo;

    @FXML
    private ComboBox<String> reviewEntryCombo;

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private TextArea outputArea;

    @FXML
    private Label reviewEntryMessage;

    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void initialize() {
        reviewTypeCombo.getItems().setAll("Book", "Album", "Series");
        reviewEntryMessage.setVisible(false);
    }

    @FXML
    private void onReviewTypeSelected() {
        String type = reviewTypeCombo.getValue();
        reviewEntryCombo.getItems().clear();

        if (type != null) {
            switch (type) {
                case "Book":
                    for (int i = 0; i < library.getBookCount(); i++) {
                        if("Completed".equalsIgnoreCase(library.getBookEntry(i).getStatus())){
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                        }
                    }
                    break;

                case "Album":
                    for (int i = 0; i < library.getAlbumCount(); i++) {
                        if("Completed".equalsIgnoreCase(library.getAlbumEntry(i).getStatus())){
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                        }
            
                    }
                    break;

                case "Series":
                    for (int i = 0; i < library.getSeriesCount(); i++) {
                        if("Completed".equalsIgnoreCase(library.getSeriesEntry(i).getStatus())){
                            reviewEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                        }
                        
                    }
            }
        }
    }

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
                library.reviewBookAt(index, review);
            } else if ("Album".equals(type)) {
                library.reviewAlbumAt(index, review);
            } else if ("Series".equals(type)) {
                library.reviewSeriesAt(index, review);
            }

            reviewEntryMessage.setStyle("-fx-text-fill: GREEN");
            reviewEntryMessage.setText("Entry reviewed.");
            reviewEntryMessage.setVisible(true);

            clearReviewEntry();
        } else {
            reviewEntryMessage.setStyle("-fx-text-fill: RED");
            reviewEntryMessage.setText("Please select both a type and an entry to remove, and add a rating.");
            reviewEntryMessage.setVisible(true);
        }
    }

    @FXML
    private void clearReviewEntry() {
        reviewTypeCombo.setValue(null);
        reviewEntryCombo.getItems().clear();
        reviewTextArea.clear();
    }
}