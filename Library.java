import java.util.*;

/**
 * The class <code>Library</code> contains a book library, album library, and series library, along with respective methods for
 * adding, removing, displaying, and summarizing entries/libraries,
 */

public class Library
{
    private Book[] bookLibrary;
    private Album[] albumLibrary;
    private Series[] seriesLibrary;
    private int bookCount;
    private int albumCount;
    private int seriesCount;
    private final int MAXCOUNT = 100;   //Maximum count of media entries

    //CONSTRUCTOR
    /**
      * Creates a Library containing an array of Book, Album, and Series, for book entries, album entries, and series entries, respectively, with a capacity of 100 each.
      * The count of books, albums, and series (to keep track of existing entries) are initialized to zero.
     */
    public Library()
    {
        bookLibrary = new Book[MAXCOUNT];
        albumLibrary = new Album[MAXCOUNT];
        seriesLibrary = new Series[MAXCOUNT];

        bookCount = 0;
        albumCount = 0;
        seriesCount = 0;
    }

    //METHODS
    //For adding entries:
    /**
     * Adds a book to the book library
     */
    public void addBook()
    {
        String title;
        String author;
        String genre;
        int chapterCount;

        if(bookCount < MAXCOUNT)        //If there are still slots for an entry
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
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Book Entries reached.");
        
        System.out.println("");
    }

    /**
     * Adds an album to the book library
     */
    public void addAlbum()
    {
        String title;
        String artist;
        String genre;
        int trackCount;

        if(albumCount < MAXCOUNT)       //If there are still slots for an entry
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
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Album Entries reached.");
        
        System.out.println("");
    }

    /**
     * Adds a series to the series library
     */
    public void addSeries()
    {
        String title;
        String genre;
        int seasonCount;

        if(seriesCount < MAXCOUNT)       //If there are still slots for an entry
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Title: ");
            title = sc.nextLine();
            System.out.print("Genre: ");
            genre = sc.nextLine();
            System.out.print("No. of Seasons: ");
            seasonCount = sc.nextInt();

            Series series = new Series(title, genre, seasonCount);
            System.out.println("");
            series.addEpisodes();
            seriesLibrary[seriesCount] = series;

            seriesCount++;
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Series Entries reached.");
        
        System.out.println("");
    }


    //For displaying media type entries:
    /**
     * Displays the given book's details (title, author, genre, and no. of chapters)
     * @param book the book whose detalls shall be displayed
     */
    public void displayBookEntry(Book book)
    {
        System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor());
        System.out.println("\tGenre: " + book.getGenre() + "  |  No. of Chapters: " + book.getChapterCount());
        if(book.getStatus().equalsIgnoreCase("Completed"))
        {
            System.out.println("\tRating: " + book.getRating());
            System.out.println("\tReview: " + book.getReview());
        }
        System.out.println("");
    }

    /**
     * Displays the given album's details (title, artist, genre, and no. of tracks)
     * @param album the album whose detalls shall be displayed
     */
    public void displayAlbumEntry(Album album)
    {
        System.out.println("\"" + album.getTitle() + "\" by " + album.getArtist());
        System.out.println("\tGenre: " + album.getGenre() + "  |  No. of Tracks: " + album.getTrackCount());
        if(album.getStatus().equalsIgnoreCase("Completed"))
        {
            System.out.println("\tRating: " + album.getRating());
            System.out.println("\tReview: " + album.getReview());
        }
        System.out.println("");
    }

    /**
     * Displays the given series' details (title, genre, no. of seasons, and no. of episodes)
     * @param series the series whose detalls shall be displayed
     */
    public void displaySeriesEntry(Series series)
    {
        System.out.println("\"" + series.getTitle() + "\"");
        System.out.println("\tGenre: " + series.getGenre() + "  |  No. of Seasons: " + series.getSeasonCount() + "  |  No. of Episodes: " + series.getEpisodeCount());
        if(series.getStatus().equalsIgnoreCase("Completed"))
        {
            System.out.println("\tRating: " + series.getRating());
            System.out.println("\tReview: " + series.getReview());
        }
        System.out.println("");
    }


    //For displaying media type libraries:
    /**
     * Displays all entries in the book library
     */
    public void displayBookLibrary()
    {
        int i;

        if(bookCount==0)
        {
            System.out.println("Your Book Library is empty.");
        } else
        {
            System.out.println("BOOK LIBRARY");
            for(i=0; i<bookCount; i++)
            {
                System.out.print("[" + (i+1) + "]");
                displayBookEntry(bookLibrary[i]);
            }
        }
    }

    /**
     * Displays all entries in the album library
     */
    public void displayAlbumLibrary()
    {
        int i;

        if(albumCount==0)
        {
            System.out.println("Your Album Library is empty.");
        } else
        {
            System.out.println("ALBUM LIBRARY");
            for(i=0; i<albumCount; i++)
            {
                System.out.print("[" + (i+1) + "]");
                displayAlbumEntry(albumLibrary[i]);
            }
        }
    }

    /**
     * Displays all entries in the series library
     */
    public void displaySeriesLibrary()
    {
        int i;

        if(seriesCount==0)
        {
            System.out.println("Your Series Library is empty.");
        } else
        {
            System.out.println("SERIES LIBRARY");
            for(i=0; i<seriesCount; i++)
            {
                System.out.print("[" + (i+1) + "]");
                displaySeriesEntry(seriesLibrary[i]);
            }
        }
    }


    //For removing entries:
    /**
     * Removes a book in the book library
     */
    public void removeBook()
    {
        int index;
        displayBookLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Book Number to Remove: ");
        index = sc.nextInt() - 1;

        if(index >= 0 && index < bookCount)
        {
            String removedTitle = bookLibrary[index].getTitle();           //Save before shifting
            String removedAuthor = bookLibrary[index].getAuthor();          //save before shifting
            
            int j;
            for(j=index; j<bookCount-1; j++){
                bookLibrary[j] = bookLibrary[j+1];
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
    public void removeAlbum()
    {
        int index;
        displayAlbumLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Album Number to Remove: ");
        index = sc.nextInt() - 1;

        if(index >= 0 && index < albumCount)
        {
            String removedTitle = albumLibrary[index].getTitle();
            String removedArtist = albumLibrary[index].getArtist();

            int j;
            for(j=index; j<albumCount-1; j++)
                albumLibrary[j] = albumLibrary[j+1];

            albumLibrary[j] = null;
            albumCount--;

            System.out.println("\n\"" + removedTitle + "\" by " + removedArtist + " is removed.\n");
        } else
        System.out.println("Invalid Album Number. Exiting Album Removal...");
    }

    /**
     * Removes a series in the series library
     */
    public void removeSeries()
    {
        int index;
        displaySeriesLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Series Number to Remove: ");
        index = sc.nextInt() - 1;

        if(index >= 0 && index < seriesCount)
        {
            String removedTitle = seriesLibrary[index].getTitle();

            int j;
            for(j=index; j<seriesCount-1; j++)
                seriesLibrary[j] = seriesLibrary[j+1];

            seriesLibrary[j] = null;
            seriesCount--;

            System.out.println("\n\"" + removedTitle + "\" is removed.\n");
        } else
        System.out.println("Invalid Series Number. Exiting Series Removal...");
    }


    //For displaying all entries
    /**
      * Displays all the entries (books, albums, and series) without any filters
     */
    public void displayLibrary()
    {
        displayBookLibrary();   //Display all the book entries
        System.out.println("");

        displayAlbumLibrary();   //Display all the album entries
        System.out.println("");

        displaySeriesLibrary();   //Display all the series entries
        System.out.println("");
    }

    /**
      * Displays all the entries (books, albums, and series) with the given status
      * @param status the String containing the status condition (Planned, In Progress, or Completed)
     */
    public void filterByStatus(String status)
    {
        int i;
        int statusBookCount = 0;
        int statusAlbumCount = 0;
        int statusSeriesCount = 0;

        System.out.println("BOOK LIBRARY [" + status + "]");    //Display all book entries with the given status
        for(i=0; i<bookCount; i++)
        {
            if(bookLibrary[i].getStatus().equalsIgnoreCase(status))
            {
                System.out.print("[" + (i+1) + "] ");
                displayBookEntry(bookLibrary[i]);
                statusBookCount++;
            }
        }

        if(statusBookCount==0)
            System.out.println("Your Book Library [" + status + "] is empty.");

        System.out.println("");

        System.out.println("ALBUM LIBRARY [" + status + "]");    //Display all album entries with the given status
        for(i=0; i<albumCount; i++)
        {
            if(albumLibrary[i].getStatus().equalsIgnoreCase(status))
            {
                System.out.print("[" + (i+1) + "] ");
                displayAlbumEntry(albumLibrary[i]);
                statusAlbumCount++;
            }
        }

        if(statusAlbumCount==0)
            System.out.println("Your Album Library [" + status + "] is empty.");

        System.out.println("");

        System.out.println("SERIES LIBRARY [" + status + "]");    //Display all series entries with the given status
        for(i=0; i < seriesCount; i++)
        {
            if(seriesLibrary[i].getStatus().equalsIgnoreCase(status))
            {
                System.out.print("[" + (i+1) + "] ");
                displaySeriesEntry(seriesLibrary[i]);
                statusSeriesCount++;
            }
        }

        if(statusSeriesCount==0)
            System.out.println("Your Series Library [" + status + "] is empty.");

        System.out.println("");
    }

    /**
      * Displays all the entries (books, albums, and series) with the given media type
      * @param type the String containing the medita type condition (Book, Album, or Series)
     */
    public void filterByType(String type)
    {
        if(type.equalsIgnoreCase("Book"))
        {
            displayBookLibrary();
            System.out.println("");
        }
        else if(type.equalsIgnoreCase("Album"))
        {
            displayAlbumLibrary();
            System.out.println("");
        }
        else if (type.equalsIgnoreCase("Series"))
        {
            displaySeriesLibrary();
            System.out.println("");
        }
    }

    /**
      * Displays all the entries (books, albums, and series) with the given status and media type
      * @param status the String containing the status condition (Planned, In Progress, or Completed)
      * @param type the String containing the medita type condition (Book, Album, or Series)
     */
    public void filterByStatusType(String status, String type)
    {
        int i;
        int statusTypeCount = 0;

        if(type.equalsIgnoreCase("Book"))
        {
            System.out.println("BOOK LIBRARY [" + status + "]");    //Display all book entries with the given status
            for(i=0; i<bookCount; i++)
            {
                if(bookLibrary[i].getStatus().equalsIgnoreCase(status))
                {
                    System.out.print("[" + (i+1) + "] ");
                    displayBookEntry(bookLibrary[i]);
                    statusTypeCount++;
                }
            }

            if(statusTypeCount==0)
                System.out.println("Your Book Library [" + status + "] is empty.");
            
            System.out.println("");

        } else if(type.equalsIgnoreCase("Album"))
        {
            System.out.println("ALBUM LIBRARY [" + status + "]");    //Display all album entries with the given status
            for(i=0; i<albumCount; i++)
            {
                if(albumLibrary[i].getStatus().equalsIgnoreCase(status))
                {
                    System.out.print("[" + (i+1) + "] ");
                    displayAlbumEntry(albumLibrary[i]);
                    statusTypeCount++;
                }
            }

            if(statusTypeCount==0)
                System.out.println("Your Album Library [" + status + "] is empty.");

            System.out.println("");

        } else if (type.equalsIgnoreCase("Series"))
        {
            System.out.println("SERIES LIBRARY [" + status + "]");    //Display all series entries with the given status
            for(i=0; i < seriesCount; i++)
            {
                if(seriesLibrary[i].getStatus().equalsIgnoreCase(status))
                {
                    System.out.print("[" + (i+1) + "] ");
                    displaySeriesEntry(seriesLibrary[i]);
                    statusTypeCount++;
                }
            }

            if(statusTypeCount==0)
                System.out.println("Your Series Library [" + status + "] is empty.");
            
            System.out.println("");
        }
    }

    //For summary
    /**
      * Displays the library's:<br>
      *     1. total entry count per media type<br>
      *     2. total entry count of all media types<br>
      *     3. total entry count per status<br>
      *     4. average rating of completed entries<br>
     */
    public void summary()
    {
        int i;                  //loop variable
        int plannedCount = 0;           //stores the total count of planned entries
        int inProgressCount = 0;        //stores the total count of inprogress entries
        int completedBookCount = 0;     //stores the total count of completed book entries
        int completedAlbumCount = 0;    //stores the total count of completed album entries
        int completedSeriesCount = 0;   //stores the total count of completed series entries
        Book[] completedBooks = new Book[MAXCOUNT];         //stores the completed books (to be used for getting average rating)
        Album[] completedAlbums = new Album[MAXCOUNT];      //stores the completed balbums (to be used for getting average rating)
        Series[] completedSeries = new Series[MAXCOUNT];    //stores the completed series (to be used for getting average rating)
        double aveBookRating = 0;      //stores the average rating of completed book entries
        double aveAlbumRating = 0;     //stores the average rating of completed book entries
        double aveSeriesRating = 0;    //stores the average rating of completed book entries

        System.out.println("YOUR LIBRARY SUMMARY");

        System.out.println("Total Books in Your Library: " + bookCount);    //Display total book in library
        System.out.println("Total Albums in Your Library: " + albumCount);  //Display total albums in library
        System.out.println("Total Series in Your Library: " + seriesCount); //Display total series in library

        System.out.println("\n\tTotal Entries in Your Library: " + (bookCount+albumCount+seriesCount));   //Display total entries in library
        System.out.println("");

        for(i=0; i<bookCount; i++)  //Get status counts for book entries
        {
            if(bookLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if(bookLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if(bookLibrary[i].getStatus().equalsIgnoreCase("Completed"))
            {
                completedBooks[completedBookCount] = bookLibrary[i];
                completedBookCount++;
            }
        }

        for(i=0; i<albumCount; i++)  //Get status counts for album entries
        {
            if(albumLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if(albumLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if(albumLibrary[i].getStatus().equalsIgnoreCase("Completed"))
            {
                completedAlbums[completedAlbumCount] = albumLibrary[i];
                completedAlbumCount++;
            }
        }

        for(i=0; i<seriesCount; i++)  //Get status counts for series entries
        {
            if(seriesLibrary[i].getStatus().equalsIgnoreCase("Planned"))
                plannedCount++;
            if(seriesLibrary[i].getStatus().equalsIgnoreCase("In Progress"))
                inProgressCount++;
            if(seriesLibrary[i].getStatus().equalsIgnoreCase("Completed"))
            {
                completedSeries[completedSeriesCount] = seriesLibrary[i];
                completedSeriesCount++;
            }
        }

        System.out.println("Total Entries Planned in Your Library: " + plannedCount);    //Display total book in library
        System.out.println("Total Entries In Progress in Your Library: " + inProgressCount);  //Display total albums in library
        System.out.println("Total Entries Completed in Your Library: " + (completedBookCount+completedAlbumCount+completedSeriesCount)); //Display total series in library
        System.out.println("");

        //Compute for average book rating
        if(completedBookCount > 0){
            for(i=0; i<completedBookCount; i++){
                 aveBookRating += completedBooks[i].getRating();
            }
            aveBookRating /= (completedBookCount * 1.0);
            aveBookRating = Math.round(aveBookRating * 100.0)/100.0;
        }
        

        //Compute for average album rating
        if(completedAlbumCount > 0){
            for(i=0; i<completedAlbumCount; i++){
                 aveAlbumRating += completedAlbums[i].getRating();
            }
            aveAlbumRating /= (completedAlbumCount * 1.0);
            aveAlbumRating = Math.round(aveAlbumRating * 100.0)/100.0;
        }
        

        //Compute for average series rating
        if(completedSeriesCount > 0){
            for(i=0; i<completedSeriesCount; i++){
               aveSeriesRating += completedSeries[i].getRating();
            }
            aveSeriesRating /= (completedSeriesCount * 1.0);
            aveSeriesRating = Math.round(aveSeriesRating * 100.0)/100.0;
        }
        

        System.out.println("Your Average Book Rating: " + String.format("%.2f", aveBookRating));       //Display average book rating
        System.out.println("Your Average Album Rating: " + String.format("%.2f",aveAlbumRating));     //Display average album rating
        System.out.println("Your Average Series Rating: " + String.format("%.2f",aveSeriesRating));   //Display average series rating

        int totalCompleted = completedBookCount + completedAlbumCount + completedSeriesCount;
        if(totalCompleted > 0){
            double totalAve = (aveBookRating*completedBookCount + aveAlbumRating*completedAlbumCount + aveSeriesRating*completedSeriesCount)
                                /(completedBookCount+completedAlbumCount+completedSeriesCount);
            totalAve = Math.round(totalAve * 100.0) / 100.0;
            System.out.println("\n\tYour Total Average Rating: " + String.format("%.2f", totalAve)); //Display average rating of all entries
        }
        else{
            System.out.println("\n\tYour Total Average Rating: No completed entries");
        }
        System.out.println("");
    }


    //GETTERS
    /**
      * Returns the total number of book entries in the library
      * @return the total number of book entries
     */
    public int getBookCount()
    {
        return bookCount;
    }

    /**
      * Returns the total number of album entries in the library
      * @return the total number of album entries
     */
    public int getAlbumCount()
    {
        return albumCount;
    }

    /**
      * Returns the total number of series entries in the library
      * @return the total number of series entries
     */
    public int getSeriesCount()
    {
        return seriesCount;
    }

    /**
      * Returns a book entry of its given index in the library
      * @param index the index of the book entry
      * @return a book entry
     */
    public Book getBookEntry(int index)
    {
        return bookLibrary[index];
    }

    /**
      * Returns an album entry of its given index in the library
      * @param index the index of the album entry
      * @return an album entry
     */
    public Album getAlbumEntry(int index)
    {
        return albumLibrary[index];
    }

    /**
      * Returns a series entry of its given index in the library
      * @param index the index of the series entry
      * @return a series entry
     */
    public Series getSeriesEntry(int index)
    {
        return seriesLibrary[index];
    }
}
