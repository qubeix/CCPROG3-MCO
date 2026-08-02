package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Controller class for handling removal of library entries.
 * Provides methods to select and delete books, albums, or series from the user's library.
 */
public class RemoveEntryController {

    /** Combo box for selecting the type of entry to remove (Book, Album, Series). */
    @FXML
    private ComboBox<String> removeTypeCombo;

    /** Combo box for selecting a specific entry of the chosen type. */
    @FXML
    private ComboBox<String> removeEntryCombo;

    /** Label for displaying success or error messages related to removal. */
    @FXML
    private Label removeEntryLabel;

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
     * Set up choices in the removeTypeCombo with the options (Book, Album, Series),
     * and hides the removeEntryLabel by deafult until it is needed
     */
    @FXML
    private void initialize() {
        removeTypeCombo.getItems().setAll("Book", "Album", "Series");
        removeEntryLabel.setVisible(false);
    }

    /**
     * Handles the selection of a type for removal.
     * 
     * clears the removeEntryCombo items and fills them based on the selected entry
     * types (book, album, series). all entries of the chosen type are lsited with
     * their index and title, allowign the user to chose which entry to remove
     */
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

    /**
     * Confirms and remove the selected entry from the library
     * 
     * retrives the selected entry type (book, album, series) and chosen entry. If
     * both are valid, the corresponding entry is remvoed from the library and
     * displays a successfully removed message. If either are missing, and error
     * message would be shown
     */
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

    /**
     * clears the current remove entry form
     * 
     * resets the removeTypeCombo and removeEntryCombo selection to null and clears
     * the removeEntryCombo field, preparing the form for a new entry removal
     */
    @FXML
    private void clearRemoveEntry() {
        removeTypeCombo.setValue(null);
        removeEntryCombo.getItems().clear();
    }

}
