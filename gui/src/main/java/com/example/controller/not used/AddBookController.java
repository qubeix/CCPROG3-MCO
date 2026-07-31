package com.example.controller;

import java.io.IOException;

import com.example.model.NumberTextField;

//import com.example.model.*;
//import com.example.controller.*;
//import com.example.MediaVault;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
//import javafx.scene.text.*;
//import javafx.scene.*;

public class AddBookController extends StackPane {

    @FXML
    private Label text;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField bookAuthor;

    @FXML
    private ToggleGroup genreGroup;

    @FXML
    private RadioButton romanceButton;

    @FXML
    private RadioButton mysteryButton;

    @FXML
    private RadioButton fantasyButton;

    @FXML
    private RadioButton nonfictionButton;

    @FXML
    private RadioButton selfhelpButton;

    @FXML
    private NumberTextField bookChapters;

    @FXML
    private Button submitBookButton;

    public AddBookController() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/view/addbook-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @FXML
    public void submitBook() throws IOException {
        // fill in
    }
}
