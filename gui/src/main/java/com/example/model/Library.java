package com.example.model;

import java.util.Scanner;

/**
 * The class <code>Library</code> contains a book library, album library, and
 * series library, along with respective methods for
 * adding, removing, displaying, and summarizing entries/libraries,
 */

public class Library {
    private Book[] bookLibrary;
    private Album[] albumLibrary;
    private Series[] seriesLibrary;
    private int bookCount;
    private int albumCount;
    private int seriesCount;
    private final int MAXCOUNT = 100; // Maximum count of media entries

    // private Feedbacks bookReviews;
    // private Feedbacks albumReviews;
    // private Feedbacks seriesReviews;

    // CONSTRUCTOR
    /**
     * Creates a Library containing an array of Book, Album, and Series, for book
     * entries, album entries, and series entries, respectively, with a capacity of
     * 100 each.
     * The count of books, albums, and series (to keep track of existing entries)
     * are initialized to zero.
     */
    public Library() {
        bookLibrary = new Book[MAXCOUNT];
        albumLibrary = new Album[MAXCOUNT];
        seriesLibrary = new Series[MAXCOUNT];

        bookCount = 0;
        albumCount = 0;
        seriesCount = 0;
    }

    // START OF GUI IMPLEMENTATION//

    // ADDING ENTRIES
    /**
     * Accepts a title, author, genre, and count of chapters as parameters, and
     * creates a new Book object if the chapter count is less than MAXCOUNT.
     * The new Book is added to the library array and the book count is incremented.
     * 
     * @param title        the title of the book
     * @param author       the author of the book
     * @param genre        the type of book genre
     * @param chapterCount number of chapters for the book
     * @return the newly created Book if successful, or null if the chapter count
     *         exceeds MAXCOUNT
     */
    public Book addBookInput(String title, String author, String genre, int chapterCount) {
        Book book = null;
        if (bookCount < MAXCOUNT) {
            book = new Book(title, author, genre, chapterCount);
            bookLibrary[bookCount] = book;
            bookCount++;
        }
        return book;
    }

    /**
     * Accepts a title, artist, genre, and count of chapters as parameters, and
     * creates a new Album object if the chapter count is less than MAXCOUNT.
     * The new Album is added to the library array and the album count is
     * incremented.
     * 
     * @param title      the title of the album
     * @param arist      the singer/s of the song/album
     * @param genre      the type of music genre
     * @param trackCount number of track in the album
     * @return the newly created Book if successful, or null if the chapter count
     *         exceeds MAXCOUNT
     */
    public Album addAlbumInput(String title, String artist, String genre, int trackCount) {
        Album album = null;
        if (albumCount < MAXCOUNT) {
            album = new Album(title, artist, genre, trackCount);
            albumLibrary[albumCount] = album;
            albumCount++;
        }
        return album;
    }

    /**
     * Accepts a title, genre, count of seasons, and count of episodes per seasons
     * (as an array) as parameters, and
     * creates a new Series object if the chapter count is less than MAXCOUNT.
     * The new Series is added to the library array and the series count is
     * incremented.
     * 
     * @param title        the title of the album
     * @param genre        the type of music genre
     * @param seasonCount  number of seasons in the series
     * @param episodeCount number of episdoes per series
     * @return the newly created Book if successful, or null if the chapter count
     *         exceeds MAXCOUNT
     */
    public Series addSeriesInput(String title, String genre, int seasonCount, int[] episodeCount) {
        Series series = null;
        if (seriesCount < MAXCOUNT) {
            series = new Series(title, genre, seasonCount, episodeCount);
            seriesLibrary[seriesCount] = series;
            seriesCount++;
        }
        return series;
    }

    // REMOVING ENTRIES
    // remove book
    public String removeBookAt(int index) {
        if (index >= 0 && index < bookCount) {
            String removedTitle = bookLibrary[index].getTitle() + " by " + bookLibrary[index].getAuthor();
            for (int i = index; i < bookCount - 1; i++) {
                bookLibrary[i] = bookLibrary[i + 1];
            }
            bookLibrary[bookCount - 1] = null;
            bookCount--;
            return removedTitle;
        }
        return null;
    }

    // remove album
    public String removeAlbumAt(int index) {
        if (index >= 0 && index < albumCount) {
            String removedTitle = albumLibrary[index].getTitle() + " by " + albumLibrary[index].getArtist();
            for (int i = index; i < albumCount - 1; i++) {
                albumLibrary[i] = albumLibrary[i + 1];
            }
            albumLibrary[albumCount - 1] = null;
            albumCount--;
            return removedTitle;
        }
        return null;
    }

    // remove series
    public String removeSeriesAt(int index) {
        if (index >= 0 && index < seriesCount) {
            String removedTitle = seriesLibrary[index].getTitle();
            for (int i = index; i < seriesCount - 1; i++) {
                seriesLibrary[i] = seriesLibrary[i + 1];
            }
            seriesLibrary[seriesCount - 1] = null;
            seriesCount--;
            return removedTitle;
        }
        return null;
    }

    // remove album
    public String rateBookAt(int index, int rating) {
        if (index >= 0 && index < bookCount) {
            String ratedTitle = bookLibrary[index].getTitle();
            bookLibrary[index].addRating(rating);
            return ratedTitle;
        }
        return null;
    }

    // remove album
    public String rateAlbumAt(int index, int rating) {
        if (index >= 0 && index < albumCount) {
            String ratedTitle = albumLibrary[index].getTitle();
            albumLibrary[index].addRating(rating);
            return ratedTitle;
        }
        return null;
    }

    // remove series
    public String rateSeriesAt(int index, int rating) {
        if (index >= 0 && index < seriesCount) {
            String ratedTitle = seriesLibrary[index].getTitle();
            seriesLibrary[index].addRating(rating);
            return ratedTitle;
        }
        return null;
    }

    // review book
    public String reviewBookAt(int index, String review) {
        if (index >= 0 && index < bookCount) {
            String reviewedTitle = bookLibrary[index].getTitle();
            bookLibrary[index].addReview(review);
            return reviewedTitle;
        }
        return null;
    }

    // review album
    public String reviewAlbumAt(int index, String review) {
        if (index >= 0 && index < albumCount) {
            String reviewedTitle = albumLibrary[index].getTitle();
            albumLibrary[index].addReview(review);
            return reviewedTitle;
        }
        return null;
    }

    // review series
    public String reviewSeriesAt(int index, String review) {
        if (index >= 0 && index < seriesCount) {
            String reviewedTitle = seriesLibrary[index].getTitle();
            seriesLibrary[index].addReview(review);
            return reviewedTitle;
        }
        return null;
    }

    // update book
    public String updateBookAt(int index) {
        if (index >= 0 && index < bookCount) {
            String updatedTitle = bookLibrary[index].getTitle();
            bookLibrary[index].updateProgress();
            return updatedTitle;
        }
        return null;
    }

    // update album
    public String updateAlbumAt(int index) {
        if (index >= 0 && index < albumCount) {
            String updatedTitle = albumLibrary[index].getTitle();
            albumLibrary[index].updateProgress();
            return updatedTitle;
        }
        return null;
    }

    // update series
    public String updateSeriesAt(int index) {
        if (index >= 0 && index < seriesCount) {
            String updatedTitle = seriesLibrary[index].getTitle();
            seriesLibrary[index].updateProgress();
            return updatedTitle;
        }
        return null;
    }

    // ALL ENTRIES
    public String getAllEntriesText() {
        StringBuilder sb = new StringBuilder();

        // book section
        if (bookCount == 0) {
            sb.append("Your Book Library is empty.\n");
        } else {
            sb.append("Book Library\n");
            for (int i = 0; i < bookCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(bookLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        // album section
        if (albumCount == 0) {
            sb.append("Your Album Library is empty.\n");
        } else {
            sb.append("Music Library\n");
            for (int i = 0; i < albumCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(albumLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        // series section
        if (seriesCount == 0) {
            sb.append("Your Series Library is empty.\n");
        } else {
            sb.append("Series Library\n");
            for (int i = 0; i < seriesCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(seriesLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        return sb.toString();
    }

    // SUMMARIES OF ALL ENTRIES
    public String getSummaryText() {
        StringBuilder sb = new StringBuilder();

        int plannedCount = 0;
        int inProgressCount = 0;
        int completedBookCount = 0;
        int completedAlbumCount = 0;
        int completedSeriesCount = 0;
        Book[] completedBooks = new Book[MAXCOUNT];
        Album[] completedAlbums = new Album[MAXCOUNT];
        Series[] completedSeries = new Series[MAXCOUNT];
        double aveBookRating = 0;
        double aveAlbumRaiting = 0;
        double aveSeriesRating = 0;

        sb.append("LIBRARY SUMMARY\n");
        sb.append("Total Books: ").append(bookCount).append("\n");
        sb.append("Total Album: ").append(albumCount).append("\n");
        sb.append("Total Series: ").append(seriesCount).append("\n");
        sb.append("\n\tTotal Entries: ").append(bookCount + albumCount + seriesCount).append("\n\n");

        // book
        for (int i = 0; i < bookCount; i++) {
            if (bookLibrary[i].getStatus().equalsIgnoreCase("Planned")) {
                plannedCount++;
            }
            if (bookLibrary[i].getStatus().equalsIgnoreCase("In Progress")) {
                inProgressCount++;
            }
            if (bookLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedBooks[completedBookCount++] = bookLibrary[i];
            }
        }

        // album
        for (int i = 0; i < albumCount; i++) {
            if (albumLibrary[i].getStatus().equalsIgnoreCase("Planned")) {
                plannedCount++;
            }
            if (albumLibrary[i].getStatus().equalsIgnoreCase("In Progress")) {
                inProgressCount++;
            }
            if (albumLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedAlbums[completedAlbumCount++] = albumLibrary[i];
            }
        }

        // series
        for (int i = 0; i < seriesCount; i++) {
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("Planned")) {
                plannedCount++;
            }
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("In Progress")) {
                inProgressCount++;
            }
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedSeries[completedSeriesCount++] = seriesLibrary[i];
            }
        }

        sb.append("Total Entries Planned: ").append(plannedCount).append("\n");
        sb.append("Total Entries In Progress: ").append(inProgressCount).append("\n");
        sb.append("Total Entries Completed: ").append(completedBookCount + completedAlbumCount + completedSeriesCount)
                .append("\n\n");

        if (completedBookCount > 0) {
            for (int i = 0; i < completedBookCount; i++) {
                aveBookRating += completedBooks[i].getRating();
            }
            aveBookRating /= (completedBookCount * 1.0);
            aveBookRating = Math.round(aveBookRating * 100.0) / 100.0;
        }

        if (completedAlbumCount > 0) {
            for (int i = 0; i < completedAlbumCount; i++) {
                aveAlbumRaiting += completedAlbums[i].getRating();
            }
            aveAlbumRaiting /= (completedAlbumCount * 1.0);
            aveAlbumRaiting = Math.round(aveAlbumRaiting * 100.0) / 100.0;
        }

        if (completedSeriesCount > 0) {
            for (int i = 0; i < completedSeriesCount; i++) {
                aveSeriesRating += completedSeries[i].getRating();
            }
            aveSeriesRating /= (completedSeriesCount * 1.0);
            aveSeriesRating = Math.round(aveSeriesRating * 100.0) / 100.0;
        }

        sb.append("Average Book Raiting: ").append(String.format("%.2f", aveBookRating)).append("\n");
        sb.append("Average Album Raiting: ").append(String.format("%.2f", aveAlbumRaiting)).append("\n");
        sb.append("Average Series Raiting: ").append(String.format("%.2f", aveSeriesRating)).append("\n");

        int totalCompelted = completedBookCount + completedAlbumCount + completedSeriesCount;

        if (totalCompelted > 0) {
            double totalAve = (aveBookRating * completedBookCount + aveAlbumRaiting * completedAlbumCount
                    + aveSeriesRating * completedSeriesCount) / totalCompelted;
            totalAve = Math.round(totalAve * 100.0) / 100.0;
            sb.append("\n\tTotal Average Rating: ").append(String.format("%.2f", totalAve)).append("\n");
        } else {
            sb.append("\n\tTotal Average Raiting: No Completed Entries\n");
        }

        return sb.toString();
    }

    // GETTERS
    /**
     * Returns the total number of book entries in the library
     * 
     * @return the total number of book entries
     */
    public int getBookCount() {
        return bookCount;
    }

    /**
     * Returns the total number of album entries in the library
     * 
     * @return the total number of album entries
     */
    public int getAlbumCount() {
        return albumCount;
    }

    /**
     * Returns the total number of series entries in the library
     * 
     * @return the total number of series entries
     */
    public int getSeriesCount() {
        return seriesCount;
    }

    /**
     * Returns a book entry of its given index in the library
     *
     * @param index the index of the book entry
     * @return a book entry
     */
    public Book getBookEntry(int index) {
        return bookLibrary[index];
    }

    /**
     * Returns an album entry of its given index in the library
     *
     * @param index the index of the album entry
     * @return an album entry
     */
    public Album getAlbumEntry(int index) {
        return albumLibrary[index];
    }

    /**
     * Returns a series entry of its given index in the library
     *
     * @param index the index of the series entry
     * @return a series entry
     */
    public Series getSeriesEntry(int index) {
        return seriesLibrary[index];
    }

}