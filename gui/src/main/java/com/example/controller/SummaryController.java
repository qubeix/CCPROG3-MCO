package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller class for displaying a summary of the user's library.
 * Provides a method to inject the library and show its summary text.
 */
public class SummaryController {

    /** Text area for displaying the library summary. */
    @FXML
    private TextArea outputArea;

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
        outputArea.setText(library.getSummaryText());
    }
}