package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class RemoveEntryController {

    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

    @FXML
    private Label removeEntryLabel;

    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
    }

    @FXML
    private void initialize() {
        removeTypeCombo.getItems().setAll("Book", "Album", "Series");
        removeEntryLabel.setVisible(false);
    }

    @FXML
    private void onRemoveTypeSelected() {
        String type = removeTypeCombo.getValue();
        removeEntryCombo.getItems().clear();

        switch (type) {
            case "Book":
                for (int i = 0; i < library.getBookCount(); i++) {
                    removeEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
                }
                break;
            case "Album":
                for (int i = 0; i < library.getAlbumCount(); i++) {
                    removeEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
                }
                break;
            case "Series":
                for (int i = 0; i < library.getSeriesCount(); i++) {
                    removeEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
                }
        }
    }

    @FXML
    private void confirmRemoveEntry() {
        String type = removeTypeCombo.getValue();
        String selected = removeEntryCombo.getValue();

        if (type != null && selected != null) {
            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;

            if ("Book".equals(type)) {
                library.removeBookAt(index);
            } else if ("Album".equals(type)) {
                library.removeAlbumAt(index);
            } else if ("Series".equals(type)) {
                library.removeSeriesAt(index);
            }

            removeEntryLabel.setStyle("-fx-text-fill: GREEN");
            removeEntryLabel.setText("Entry removed.");
            removeEntryLabel.setVisible(true);

            clearRemoveEntry();
        } else {
            removeEntryLabel.setStyle("-fx-text-fill: RED");
            removeEntryLabel.setText("Please select both a type and an entry to remove.");
            removeEntryLabel.setVisible(true);
        }
    }

    @FXML
    private void clearRemoveEntry() {
        removeTypeCombo.setValue(null);
        removeEntryCombo.getItems().clear();
    }

}
