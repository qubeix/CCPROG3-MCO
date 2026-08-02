package com.example.model;

/**
 * The class <code>Book</code> contains the attributes, constructor, methods,
 * and getters necessary for the creation, modification, and manipulation of a
 * book entry with:<br>
 * the inherited methods of: assigning a status (and modifying it), assigning a
 * rating, and assigning a review; and<br>
 * the unique method of: updating the book entry's current chapter progress, and
 * returning its information via String
 */

 /**
 * Represents a book entry in the media library.
 * A book has a title, author, genre, and chapter count.
 * It inherits methods from MediaEntry for assigning status, rating, and review,
 * and adds functionality for tracking chapter progress.
 */
public class Book extends MediaEntry {
    // ATTRIBUTES
    /** The author of the book. */
    private final String AUTHOR;

    /** The total number of chapters in the book. */
    private final int CHAPTERCOUNT;

    /** The user's current chapter progress. */
    private int currentChapter;

    // CONSTRUCTOR
    /**
     * Accepts a title, author, genre, and count of chapters as parameters, and
     * initializes status to "Planned," rating to 0, review to empty, current
     * chapter to 0
     * 
     * @param title        the title of the book
     * @param author       the artist of the book
     * @param genre        the genre of the book
     * @param chapterCount the count of chapters in the book
     */
    public Book(String title, String author, String genre, int chapterCount) {
        super(title, genre);
        AUTHOR = author;

        CHAPTERCOUNT = chapterCount;
        currentChapter = 0;
    }

    // METHODS
    /**
     * Moves the current chapter forward by one
     */
    public void updateProgress() {
        if (currentChapter < CHAPTERCOUNT) // If the current chapter has not reached the last chapter
        {
            currentChapter++; // Move to the next chapter

            if (status.equalsIgnoreCase("Planned")) // If the status if "Planned"
                updateStatus("In Progress"); // Update the status to "In Progress"

            if (currentChapter == CHAPTERCOUNT) // If the current chapter is the last chapter
                updateStatus("Completed"); // Update the status to "Completed"
        } else // If the book is completed
            System.out.println("You have already completed this book"); // Display a message
    }

    /**
     * Returns a String containing the information of the book (title, author,
     * genre, and chapters (and if the entry is completed, rating and review))
     * 
     * @return a formatted string with book information
     */
    public String toString() {
        String info = "\"" + TITLE + "\" by " + AUTHOR;
        info += "\nGenre: " + GENRE + "  |  No. of Chapters: " + CHAPTERCOUNT + "\nCurrent Chapter: "
                + currentChapter + "\n";
        if (status.equalsIgnoreCase("Completed")) {
            info += "\tRating: " + rating;
            info += "\n\tReview: " + review;
        }
        info += "\n";

        return info;
    }

    // GETTERS
    /**
     * Returns the author of the book
     * 
     * @return the author
     */
    public String getAuthor() {
        return AUTHOR;
    }

    /**
     * Returns the number of chapters in the book
     * 
     * @return the number of chapters
     */
    public int getChapterCount() {
        return CHAPTERCOUNT;
    }

    /**
     * Returns the progress of the book, in the format of:
     * "{@literal <current chapter> / <total chapters> Chapters}"
     * 
     * @return the progress
     */
    public String getProgress() {
        return currentChapter + "/" + CHAPTERCOUNT + " Chapters\n"; // return the amount of chapters read
    }
}