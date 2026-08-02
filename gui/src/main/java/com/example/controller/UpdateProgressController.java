package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UpdateProgressController {

    @FXML
    private ComboBox<String> updateTypeCombo;

    @FXML
    private ComboBox<String> updateEntryCombo;

    @FXML
    private Label updateEntryMessage;

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
     * Initializes the update entry form.
     * 
     * Populates the updateTypeCombo with available entry types
     * ("Book", "Album", "Series") and hides the updateEntryMessage
     * label. Ensures the form is ready for user interaction
     * when the view is first loaded.
     *
     * @FXML This method is automatically called when the
     *       corresponding FXML view is initialized.
     */
    @FXML
    private void initialize() {
        updateTypeCombo.getItems().setAll("Book", "Album", "Series");
        updateEntryMessage.setVisible(false);
    }

    /**
     * Handles selection of an update type and populates the entry list.
     * 
     * Retrieves the selected type from the updateTypeCombo and clears
     * the updateEntryCombo list. Based on the selected entry types (book, album,
     * series),
     * found in the library then adds their titles to the combo box with an
     * index prefix. If no type is selected, the list remains empty.
     *
     * @FXML This method is triggered when the user selects an
     *       update type from the combo box.
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

    /**
     * Confirms and updates the progress of a selected library entry.
     * 
     * Retrieves the selected entry type (book, album, series) and entry
     * from the combo boxes. Confirms if both are selected, and checks
     * the entry's status. If the entry is marked as "Completed", displays
     * an error message preventing further updates. Otherwise, calls the
     * entry's updateProgress method and displays a success message with
     * the updated entry details. If no type or entry is selected, shows
     * an error message instead.
     *
     * @FXML This method is triggered when the user clicks the
     *       confirm button to update a library entry.
     */
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
                    library.getBookEntry(index).updateProgress();
                    // library.updateBookAt(index);
                    info = library.getBookEntry(index).toString();
                } else if ("Album".equals(type)) {
                    library.getBookEntry(index).updateProgress();
                    // library.updateAlbumAt(index);
                    info = library.getAlbumEntry(index).toString();
                } else if ("Series".equals(type)) {
                    library.getBookEntry(index).updateProgress();
                    // library.updateSeriesAt(index);
                    info = library.getSeriesEntry(index).toString();
                }

                updateEntryMessage.setStyle("-fx-text-fill: GREEN");
                updateEntryMessage.setText("Entry updated.\n" + info);
                updateEntryMessage.setVisible(true);
            }
        } else {
            updateEntryMessage.setStyle("-fx-text-fill: RED");
            updateEntryMessage.setText("Please select both a type and an entry to remove.");
            updateEntryMessage.setVisible(true);
        }
    }

    /**
     * Clears the update entry form.
     * 
     * Resets the updateTypeCombo and updateEntryCombo selections
     * to null and hides the updateEntryMessage label.
     *
     * @FXML This method is triggered when the user clicks the
     *       clear button in the update entry view.
     */
    @FXML
    private void clearUpdateEntry() {
        updateTypeCombo.setValue(null);
        updateEntryCombo.setValue(null);
        updateEntryMessage.setVisible(false);
    }
}
