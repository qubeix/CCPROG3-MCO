package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UpdateProgressController {

    @FXML
    private VBox updateEntryPanel;

    @FXML
    private ComboBox<String> updateTypeCombo;

    @FXML
    private ComboBox<String> updateEntryCombo;

    @FXML
    private Label updateEntryMessage;

    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void initialize() {
        updateTypeCombo.getItems().setAll("Book", "Album", "Series");
        updateEntryMessage.setVisible(false);
    }

    /**
     * Initializes the entry options depending on the selected media type
     */
    @FXML
    private void onUpdateTypeSelected() {
        String type = updateTypeCombo.getValue();
        updateEntryCombo.getItems().clear();

        if (type != null) {
            switch (type) {
                case "Book":
                    for (int i = 0; i < library.getBookCount(); i++) {
                        updateEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                    }
                    break;

                case "Album":
                    for (int i = 0; i < library.getAlbumCount(); i++) {
                        updateEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                    }
                    break;

                case "Series":
                    for (int i = 0; i < library.getSeriesCount(); i++) {
                        updateEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                    }
            }
        }
    }

    @FXML
    private void confirmUpdateEntry() {
        String type = updateTypeCombo.getValue();
        String selected = updateEntryCombo.getValue();

        if (type != null && selected != null) {
            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;
            String status = null;

            if ("Book".equals(type)) {
                status = library.getBookEntry(index).getStatus();
            } else if ("Album".equals(type)) {
                status = library.getAlbumEntry(index).getStatus();
            } else if ("Series".equals(type)) {
                status = library.getSeriesEntry(index).getStatus();
            }

            if ("Completed".equalsIgnoreCase(status)) {
                updateEntryMessage.setStyle("-fx-text-fill: RED");
                updateEntryMessage.setText("You can no longer update a completed entry.");
                updateEntryMessage.setVisible(true);
            } else {
                String info = null;

                if ("Book".equals(type)) {
                    library.updateBookAt(index);
                    info = library.getBookEntry(index).toString();
                } else if ("Album".equals(type)) {
                    library.updateAlbumAt(index);
                    info = library.getAlbumEntry(index).toString();
                } else if ("Series".equals(type)) {
                    library.updateSeriesAt(index);
                    info = library.getSeriesEntry(index).toString();
                }

                updateEntryMessage.setStyle("-fx-text-fill: GREEN");
                updateEntryMessage.setText("Entry updated.\n" + info);
                updateEntryMessage.setVisible(true);
            }
            // learUpdateEntry();
        } else {
            updateEntryMessage.setStyle("-fx-text-fill: RED");
            updateEntryMessage.setText("Please select both a type and an entry to remove.");
            updateEntryMessage.setVisible(true);
        }
    }

    @FXML
    private void clearUpdateEntry() {
        updateTypeCombo.setValue(null);
        updateEntryCombo.setValue(null);
        updateEntryMessage.setVisible(false);
    }
}
