package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SummaryController {

    @FXML
    private TextArea outputArea;

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