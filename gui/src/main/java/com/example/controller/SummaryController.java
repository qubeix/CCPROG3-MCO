package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SummaryController {

    @FXML
    private TextArea outputArea;

    private Library library;

    public void setLibrary(Library library) {
        this.library = library;
        outputArea.setText(library.getSummaryText());
    }
}