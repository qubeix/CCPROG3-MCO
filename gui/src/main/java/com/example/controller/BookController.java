package com.example.controller;

import com.example.model.Book;
import com.example.model.Library;
import com.example.model.NumberTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class for handling book-related input in the GUI.
 * Provides methods to add books to the library and reset input fields.
 */
public class BookController {

    /** Text field for entering the book title. */
    @FXML
    private TextField bookTitleField;

    /** Text field for entering the book author. */
    @FXML
    private TextField bookAuthorField;

     /** Toggle group for selecting the book genre. */
    @FXML
    private ToggleGroup bookGenreGroup;

    /** Custom text field for entering the number of chapters (numeric only). */
    @FXML
    private NumberTextField bookChapter;

    /** Label for displaying error or success messages. */
    @FXML
    private Label bookErrorLabel;

    /** Reference to the user's library for storing books. */
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
     * Accepts a title, author, genre, and chapter count
     * if the fileds is empty or invalid it dispals an error message in red
     * if all iputs are valid, initializes a new object and add its to the library,
     * displaying a sucessfully added message in green
     */
    @FXML
    private void confirmAddBook() {
        String title = bookTitleField.getText();
        String author = bookAuthorField.getText();
        String chapterText = bookChapter.getText();
        RadioButton selectedGenre = (RadioButton) bookGenreGroup.getSelectedToggle();

        if (title.isEmpty() || author.isEmpty() || selectedGenre == null || chapterText.isEmpty()) {
            bookErrorLabel.setStyle("-fx-text-fill: RED");
            bookErrorLabel.setText("Please fill in all fields before submitting");
            bookErrorLabel.setVisible(true);

        } else {
            try {
                int chapterCount = Integer.parseInt(chapterText);
                if (chapterCount <= 0)
                    throw new NumberFormatException();

                Book newBook = library.addBookInput(title, author, selectedGenre.getText(), chapterCount);

                bookErrorLabel.setStyle("-fx-text-fill: GREEN");
                bookErrorLabel.setText("Added: " + newBook.toString());
                bookErrorLabel.setVisible(true);

            } catch (NumberFormatException e) {
                bookErrorLabel.setStyle("-fx-text-fill: RED");
                bookErrorLabel.setText("Please enter a valid positive number for chapters.");
                bookErrorLabel.setVisible(true);
            }
        }
    }

    /**
     * Resets the album title, artist, genre selection, and track count fields
     * to their default empty.
     * This is triggered when the user clocks the "Clear" button. allowing the user
     * to start fresh without any previously entered valeus
     */
    @FXML
    private void clearAddBook() {
        bookTitleField.clear();
        bookAuthorField.clear();
        bookGenreGroup.selectToggle(null);
        bookChapter.clear();
    }
}