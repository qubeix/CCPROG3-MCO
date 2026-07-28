package com.example.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.MediaVault;
import com.example.model.*;

public class RemoveEntryController {
    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

    @FXML
    private Label statusLabel;

    private Library library;

    @FXML
    private void initialize() {
        User currentUser = MediaVaultController.getCurrentUser();
        if (currentUser != null) {
            library = currentUser.getLibrary();
        }
        removeTypeCombo.setItems(FXCollections.observableArrayList("Book", "Album", "Series"));
    }

    @FXML
    private void handleTypeSelected() {
        String type = removeTypeCombo.getValue();
        removeEntryCombo.getItems().clear();
        statusLabel.setText("");

        if (type == null || library == null) {

        }

        if (type.equals("Book")) {
            for (int i = 0; i < library.getBookCount(); i++) {
                removeEntryCombo.getItems().add(library.getBookEntry(i).getTitle());

            }
        } else if (type.equals("Album")) {
            for (int i = 0; i < library.getAlbumCount(); i++) {
                removeEntryCombo.getItems().add(library.getAlbumEntry(i).getTitle());

            }
        } else if (type.equals("Series")) {
            for (int i = 0; i < library.getSeriesCount(); i++) {
                removeEntryCombo.getItems().add(library.getSeriesEntry(i).getTitle());

            }
        }
    }

    @FXML
    private void confirmRemoveEntry() {
        String type = removeTypeCombo.getValue();
        int index = removeEntryCombo.getSelectionModel().getSelectedIndex();

        if (type == null || index < 0) {
            statusLabel.setText("Please select a type and a entry");
        }

        // String removedTitle = library.removeEntry
    }

    @FXML
    private void goBack() throws IOException {
        MediaVault.setRoot("main-menu-view");
    }
}