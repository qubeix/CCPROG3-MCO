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

    // METHODS
    // For adding entries:
    /**
     * Adds a book to the book library
     */
    @SuppressWarnings("resource")
    public void addBook() {
        String title;
        String author;
        String genre;
        int chapterCount;

        if (bookCount < MAXCOUNT) // If there are still slots for an entry
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Title: ");
            title = sc.nextLine();
            System.out.print("Author: ");
            author = sc.nextLine();
            System.out.print("Genre: ");
            genre = sc.nextLine();
            System.out.print("No. of Chapters: ");
            chapterCount = sc.nextInt();

            Book book = new Book(title, author, genre, chapterCount);
            bookLibrary[bookCount] = book;

            bookCount++;
        } else // If there are no more slots for an entry
            System.out.println("Maximum Book Entries reached.");

        System.out.println("");
    }

    /**
     * Adds an album to the book library
     */
    @SuppressWarnings("resource")
    public void addAlbum() {
        String title;
        String artist;
        String genre;
        int trackCount;

        if (albumCount < MAXCOUNT) // If there are still slots for an entry
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Title: ");
            title = sc.nextLine();
            System.out.print("Artist: ");
            artist = sc.nextLine();
            System.out.print("Genre: ");
            genre = sc.nextLine();
            System.out.print("No. of Tracks: ");
            trackCount = sc.nextInt();

            Album album = new Album(title, artist, genre, trackCount);
            albumLibrary[albumCount] = album;

            albumCount++;
        } else // If there are no more slots for an entry
            System.out.println("Maximum Album Entries reached.");

        System.out.println("");
    }

    /**
     * Adds a series to the series library
     */
    @SuppressWarnings("resource")
    public void addSeries() {
        String title;
        String studio;
        String genre;
        int seasonCount;

        if (seriesCount < MAXCOUNT) // If there are still slots for an entry
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Title: ");
            title = sc.nextLine();
            System.out.print("Studio: ");
            studio = sc.nextLine();
            System.out.print("Genre: ");
            genre = sc.nextLine();
            System.out.print("No. of Seasons: ");
            seasonCount = sc.nextInt();

            Series series = new Series(title, studio, genre, seasonCount);
            System.out.println("");
            series.addEpisodes();
            seriesLibrary[seriesCount] = series;

            seriesCount++;
        } else // If there are no more slots for an entry
            System.out.println("Maximum Series Entries reached.");

        System.out.println("");
    }

    // For displaying media type libraries:
    /**
     * Displays all entries in the book library
     */
    public void displayBookLibrary() {
        int i;

        if (bookCount == 0) {
            System.out.println("Your Book Library is empty.");
        } else {
            System.out.println("BOOK LIBRARY");
            for (i = 0; i < bookCount; i++) {
                System.out.print("[" + (i + 1) + "]");
                bookLibrary[i].toString();
            }
        }
    }

    /**
     * Displays all entries in the album library
     */
    public void displayAlbumLibrary() {
        int i;

        if (albumCount == 0) {
            System.out.println("Your Album Library is empty.");
        } else {
            System.out.println("ALBUM LIBRARY");
            for (i = 0; i < albumCount; i++) {
                System.out.print("[" + (i + 1) + "]");
                albumLibrary[i].toString();
            }
        }
    }

    /**
     * Displays all entries in the series library
     */
    public void displaySeriesLibrary() {
        int i;

        if (seriesCount == 0) {
            System.out.println("Your Series Library is empty.");
        } else {
            System.out.println("SERIES LIBRARY");
            for (i = 0; i < seriesCount; i++) {
                System.out.print("[" + (i + 1) + "]");
                seriesLibrary[i].toString();
            }
        }
    }

    // For removing entries:
    /**
     * Removes a book in the book library
     */
    @SuppressWarnings("resource")
    public void removeBook() {
        int index;
        displayBookLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Book Number to Remove: ");
        index = sc.nextInt() - 1;

        if (index >= 0 && index < bookCount) {
            String removedTitle = bookLibrary[index].getTitle(); // Save before shifting
            String removedAuthor = bookLibrary[index].getAuthor(); // save before shifting

            int j;
            for (j = index; j < bookCount - 1; j++) {
                bookLibrary[j] = bookLibrary[j + 1];
            }

            bookLibrary[j] = null;
            bookCount--;

            System.out.println("\n\"" + removedTitle + "\" by " + removedAuthor + " is removed.\n");
        } else
            System.out.println("Invalid Book Number. Exiting Book Removal...");
    }

    /**
     * Removes an album in the album library
     */
    @SuppressWarnings("resource")
    public void removeAlbum() {
        int index;
        displayAlbumLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Album Number to Remove: ");
        index = sc.nextInt() - 1;

        if (index >= 0 && index < albumCount) {
            String removedTitle = albumLibrary[index].getTitle();
            String removedArtist = albumLibrary[index].getArtist();

            int j;
            for (j = index; j < albumCount - 1; j++)
                albumLibrary[j] = albumLibrary[j + 1];

            albumLibrary[j] = null;
            albumCount--;

            System.out.println("\n\"" + removedTitle + "\" by " + removedArtist + " is removed.\n");
        } else
            System.out.println("Invalid Album Number. Exiting Album Removal...");
    }

    /**
     * Removes a series in the series library
     */
    @SuppressWarnings("resource")
    public void removeSeries() {
        int index;
        displaySeriesLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Series Number to Remove: ");
        index = sc.nextInt() - 1;

        if (index >= 0 && index < seriesCount) {
            String removedTitle = seriesLibrary[index].getTitle();

            int j;
            for (j = index; j < seriesCount - 1; j++)
                seriesLibrary[j] = seriesLibrary[j + 1];

            seriesLibrary[j] = null;
            seriesCount--;

            System.out.println("\n\"" + removedTitle + "\" is removed.\n");
        } else
            System.out.println("Invalid Series Number. Exiting Series Removal...");
    }

    // For displaying all entries
    /**
     * Displays all the entries (books, albums, and series) without any filters
     */
    public void displayLibrary() {
        displayBookLibrary(); // Display all the book entries
        System.out.println("");

        displayAlbumLibrary(); // Display all the album entries
        System.out.println("");

        displaySeriesLibrary(); // Display all the series entries
        System.out.println("");
    }

    /**
     * Displays all the entries (books, albums, and series) with the given status
     * 
     * @param status the String containing the status condition (Planned, In
     *               Progress, or Completed)
     */
    public void filterByStatus(String status) {
        int i;
        int statusBookCount = 0;
        int statusAlbumCount = 0;
        int statusSeriesCount = 0;

        System.out.println("BOOK LIBRARY [" + status + "]"); // Display all book entries with the given status
        for (i = 0; i < bookCount; i++) {
            if (bookLibrary[i].getStatus().equalsIgnoreCase(status)) {
                System.out.print("[" + (i + 1) + "] ");
                bookLibrary[i].toString();
                statusBookCount++;
            }
        }

        if (statusBookCount == 0)
            System.out.println("Your Book Library [" + status + "] is empty.");

        System.out.println("");

        System.out.println("ALBUM LIBRARY [" + status + "]"); // Display all album entries with the given status
        for (i = 0; i < albumCount; i++) {
            if (albumLibrary[i].getStatus().equalsIgnoreCase(status)) {
                System.out.print("[" + (i + 1) + "] ");
                albumLibrary[i].toString();
                statusAlbumCount++;
            }
        }

        if (statusAlbumCount == 0)
            System.out.println("Your Album Library [" + status + "] is empty.");

        System.out.println("");

        System.out.println("SERIES LIBRARY [" + status + "]"); // Display all series entries with the given status
        for (i = 0; i < seriesCount; i++) {
            if (seriesLibrary[i].getStatus().equalsIgnoreCase(status)) {
                System.out.print("[" + (i + 1) + "] ");
                seriesLibrary[i].toString();
                statusSeriesCount++;
            }
        }

        if (statusSeriesCount == 0)
            System.out.println("Your Series Library [" + status + "] is empty.");

        System.out.println("");
    }

    /**
     * Displays all the entries (books, albums, and series) with the given media
     * type
     * 
     * @param type the String containing the medita type condition (Book, Album, or
     *             Series)
     */
    public void filterByType(String type) {
        if (type.equalsIgnoreCase("Book")) {
            displayBookLibrary();
            System.out.println("");
        } else if (type.equalsIgnoreCase("Album")) {
            displayAlbumLibrary();
            System.out.println("");
        } else if (type.equalsIgnoreCase("Series")) {
            displaySeriesLibrary();
            System.out.println("");
        }
    }

    /**
     * Displays all the entries (books, albums, and series) with the given status
     * and media type
     * 
     * @param status the String containing the status condition (Planned, In
     *               Progress, or Completed)
     * @param type   the String containing the medita type condition (Book, Album,
     *               or Series)
     */
    public void filterByStatusType(String status, String type) {
        int i;
        int statusTypeCount = 0;

        if (type.equalsIgnoreCase("Book")) {
            System.out.println("BOOK LIBRARY [" + status + "]"); // Display all book entries with the given status
            for (i = 0; i < bookCount; i++) {
                if (bookLibrary[i].getStatus().equalsIgnoreCase(status)) {
                    System.out.print("[" + (i + 1) + "] ");
                    bookLibrary[i].toString();
                    statusTypeCount++;
                }
            }

            if (statusTypeCount == 0)
                System.out.println("Your Book Library [" + status + "] is empty.");

            System.out.println("");

        } else if (type.equalsIgnoreCase("Album")) {
            System.out.println("ALBUM LIBRARY [" + status + "]"); // Display all album entries with the given status
            for (i = 0; i < albumCount; i++) {
                if (albumLibrary[i].getStatus().equalsIgnoreCase(status)) {
                    System.out.print("[" + (i + 1) + "] ");
                    albumLibrary[i].toString();
                    statusTypeCount++;
                }
            }

            if (statusTypeCount == 0)
                System.out.println("Your Album Library [" + status + "] is empty.");

            System.out.println("");

        } else if (type.equalsIgnoreCase("Series")) {
            System.out.println("SERIES LIBRARY [" + status + "]"); // Display all series entries with the given status
            for (i = 0; i < seriesCount; i++) {
                if (seriesLibrary[i].getStatus().equalsIgnoreCase(status)) {
                    System.out.print("[" + (i + 1) + "] ");
                    seriesLibrary[i].toString();
                    statusTypeCount++;
                }
            }

            if (statusTypeCount == 0)
                System.out.println("Your Series Library [" + status + "] is empty.");

            System.out.println("");
        }
    }

    // For summary
    /**
     * Displays the library's:<br>
     * 1. total entry count per media type<br>
     * 2. total entry count of all media types<br>
     * 3. total entry count per status<br>
     * 4. average rating of completed entries<br>
     */
    public void summary() {
        int i; // loop variable
        int plannedCount = 0; // stores the total count of planned entries
        int inProgressCount = 0; // stores the total count of inprogress entries
        int completedBookCount = 0; // stores the total count of completed book entries
        int completedAlbumCount = 0; // stores the total count of completed album entries
        int completedSeriesCount = 0; // stores the total count of completed series entries
        Book[] completedBooks = new Book[MAXCOUNT]; // stores the completed books (to be used for getting average
                                                    // rating)
        Album[] completedAlbums = new Album[MAXCOUNT]; // stores the completed balbums (to be used for getting average
                                                       // rating)
        Series[] completedSeries = new Series[MAXCOUNT]; // stores the completed series (to be used for getting average
                                                         // rating)
        double aveBookRating = 0; // stores the average rating of completed book entries
        double aveAlbumRating = 0; // stores the average rating of completed book entries
        double aveSeriesRating = 0; // stores the average rating of completed book entries

        System.out.println("YOUR LIBRARY SUMMARY");

        System.out.println("Total Books in Your Library: " + bookCount); // Display total book in library
        System.out.println("Total Albums in Your Library: " + albumCount); // Display total albums in library
        System.out.println("Total Series in Your Library: " + seriesCount); // Display total series in library

        System.out.println("\n\tTotal Entries in Your Library: " + (bookCount + albumCount + seriesCount)); // Display
                                                                                                            // total
                                                                                                            // entries
                                                                                                            // in
                                                                                                            // library
        System.out.println("");

        for (i = 0; i < bookCount; i++) // Get status counts for book entries
        {
            if (bookLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if (bookLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if (bookLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedBooks[completedBookCount] = bookLibrary[i];
                completedBookCount++;
            }
        }

        for (i = 0; i < albumCount; i++) // Get status counts for album entries
        {
            if (albumLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if (albumLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if (albumLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedAlbums[completedAlbumCount] = albumLibrary[i];
                completedAlbumCount++;
            }
        }

        for (i = 0; i < seriesCount; i++) // Get status counts for series entries
        {
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if (seriesLibrary[i].getStatus().equalsIgnoreCase("Completed")) {
                completedSeries[completedSeriesCount] = seriesLibrary[i];
                completedSeriesCount++;
            }
        }

        System.out.println("Total Entries Planned in Your Library: " + plannedCount); // Display total book in library
        System.out.println("Total Entries In Progress in Your Library: " + inProgressCount); // Display total albums in
                                                                                             // library
        System.out.println("Total Entries Completed in Your Library: "
                + (completedBookCount + completedAlbumCount + completedSeriesCount)); // Display total series in library
        System.out.println("");

        // Compute for average book rating
        if (completedBookCount > 0) {
            for (i = 0; i < completedBookCount; i++) {
                aveBookRating += completedBooks[i].getRating();
            }
            aveBookRating /= (completedBookCount * 1.0);
            aveBookRating = Math.round(aveBookRating * 100.0) / 100.0;
        }

        // Compute for average album rating
        if (completedAlbumCount > 0) {
            for (i = 0; i < completedAlbumCount; i++) {
                aveAlbumRating += completedAlbums[i].getRating();
            }
            aveAlbumRating /= (completedAlbumCount * 1.0);
            aveAlbumRating = Math.round(aveAlbumRating * 100.0) / 100.0;
        }

        // Compute for average series rating
        if (completedSeriesCount > 0) {
            for (i = 0; i < completedSeriesCount; i++) {
                aveSeriesRating += completedSeries[i].getRating();
            }
            aveSeriesRating /= (completedSeriesCount * 1.0);
            aveSeriesRating = Math.round(aveSeriesRating * 100.0) / 100.0;
        }

        System.out.println("Your Average Book Rating: " + String.format("%.2f", aveBookRating)); // Display average book
                                                                                                 // rating
        System.out.println("Your Average Album Rating: " + String.format("%.2f", aveAlbumRating)); // Display average
                                                                                                   // album rating
        System.out.println("Your Average Series Rating: " + String.format("%.2f", aveSeriesRating)); // Display average
                                                                                                     // series rating

        int totalCompleted = completedBookCount + completedAlbumCount + completedSeriesCount;
        if (totalCompleted > 0) {
            double totalAve = (aveBookRating * completedBookCount + aveAlbumRating * completedAlbumCount
                    + aveSeriesRating * completedSeriesCount)
                    / (completedBookCount + completedAlbumCount + completedSeriesCount);
            totalAve = Math.round(totalAve * 100.0) / 100.0;
            System.out.println("\n\tYour Total Average Rating: " + String.format("%.2f", totalAve)); // Display average
                                                                                                     // rating of all
                                                                                                     // entries
        } else {
            System.out.println("\n\tYour Total Average Rating: No completed entries");
        }
        System.out.println("");
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

    /**
     * JAVAFX CODES
     */

    // remove book
    public void removeBookAt(int index) {
        if (index >= 0 && index < bookCount) {
            for (int j = index; j < bookCount - 1; j++) {
                bookLibrary[j] = bookLibrary[j + 1];
            }
            bookLibrary[bookCount - 1] = null;
            bookCount--;
        }
    }

    // remove album
    public void removeAlbumAt(int index) {
        if (index >= 0 && index < albumCount) {
            for (int j = index; j < albumCount - 1; j++) {
                albumLibrary[j] = albumLibrary[j + 1];
            }
            albumLibrary[albumCount - 1] = null;
            albumCount--;
        }
    }

    // remove series
    public void removeSeriesAt(int index) {
        if (index >= 0 && index < seriesCount) {
            for (int j = index; j < seriesCount - 1; j++) {
                seriesLibrary[j] = seriesLibrary[j + 1];
            }
            seriesLibrary[seriesCount - 1] = null;
            seriesCount--;
        }
    }

    public String getAllEntriesText() {
        StringBuilder sb = new StringBuilder();

        // book sesction
        if (bookCount == 0) {
            sb.append("Your Book Library is empty.\n");
        } else {
            sb.append("Book Library\n");
            for (int i = 0; i < bookCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(bookLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        // album sesction
        if (albumCount == 0) {
            sb.append("Your Album Library is empty.\n");
        } else {
            sb.append("Music Library\n");
            for (int i = 0; i < albumCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(albumLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        // series sesction
        if (bookCount == 0) {
            sb.append("Your Series Library is empty.\n");
        } else {
            sb.append("Series Library\n");
            for (int i = 0; i < bookCount; i++) {
                sb.append("[").append(i + 1).append("] ").append(seriesLibrary[i].toString()).append("\n");
            }
        }
        sb.append("\n");

        return sb.toString();
    }

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

}