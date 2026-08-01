package com.example.controller;

//import java.io.IOException;

import com.example.model.Book;
//import com.example.MediaVault;
import com.example.model.Library;
import com.example.model.User;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class BookController{

    // @FXML
    // private VBox addBookPanel;

    @FXML
    private TextField bookTitleField;

    @FXML
    private TextField bookAuthorField;

    @FXML
    private ToggleGroup bookGenreGroup;

    // @FXML
    // private RadioButton romanceButton;

    // @FXML
    // private RadioButton mysteryButton;

    // @FXML
    // private RadioButton fantasyButton;

    // @FXML
    // private RadioButton nonfictionButton;

    // @FXML
    // private RadioButton selfhelpButton;

    @FXML
    private NumberTextField bookChapter;

    @FXML
    private Label bookErrorLabel;

    // @FXML
    // private TextArea outputArea;

    private User currentUser;
    private Library library;

    public void setLibrary(Library library){
        this.library = library;
    }

    @FXML
    private void confirmAddBook() {
        String title = bookTitleField.getText();
        String author = bookAuthorField.getText();
        String chapterText = bookChapter.getText();
        RadioButton selectedGenre = (RadioButton) bookGenreGroup.getSelectedToggle();

        if (title.isEmpty() || author.isEmpty() || selectedGenre == null || chapterText.isEmpty()) {
            bookErrorLabel.setStyle("-fx-text-fil: RED");
            bookErrorLabel.setText("Please fill in all fields before submitting");
            bookErrorLabel.setVisible(true);
            
        }
        else{
            try {
                int chapterCount = Integer.parseInt(chapterText);
                if (chapterCount <= 0) throw new NumberFormatException();

                //library.addBookInput(title, author, selectedGenre.getText(), chapterCount);
                Book newBook = library.addBookInput(title, author, selectedGenre.getText(), chapterCount);

                bookErrorLabel.setStyle("=fx-text-fill: GREEN");
                bookErrorLabel.setText("Added: " + newBook.toString());
                bookErrorLabel.setVisible(true);

                // outputArea.setVisible(true);
                // outputArea.setManaged(true);
                // outputArea.setText("\"" + title + "\" by " + author + " added to your Book Library.");
            } catch (NumberFormatException e) {
                bookErrorLabel.setStyle("-fx-text-fill: RED");
                bookErrorLabel.setText("Please enter a valid positive number for chapters.");
                bookErrorLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void clearAddBook() {
        bookTitleField.clear();
        bookAuthorField.clear();
        bookGenreGroup.selectToggle(null);
        bookChapter.clear();
        bookErrorLabel.setVisible(false);
    }
}