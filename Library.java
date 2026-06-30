import java.util.*;

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
     * addBook() adds a book to the book library
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
            System.out.print("\nAuthor: ");
            author = sc.nextLine();
            System.out.print("\nGenre: ");
            genre = sc.nextLine();
            System.out.print("\nNo. of Chapters: ");
            chapterCount = sc.nextInt();

            Book book = new Book(title, author, genre, chapterCount);
            bookLibrary[bookCount] = book;

            bookCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Book Entries reached.");
    }

    /**
     * addAlbum() adds an album to the album library
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
            System.out.print("\nArtist: ");
            artist = sc.nextLine();
            System.out.print("\nGenre: ");
            genre = sc.nextLine();
            System.out.print("\nNo. of Tracks: ");
            trackCount = sc.nextInt();

            Album album = new Album(title, artist, genre, trackCount);
            albumLibrary[albumCount] = album;

            albumCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Album Entries reached.");
    }

    /**
     * addSeries() adds a series to the series library
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
            System.out.print("\nGenre: ");
            genre = sc.nextLine();
            System.out.print("\nNo. of Seasons: ");
            seasonCount = sc.nextInt();

            Series series = new Series(title, genre, seasonCount);
            series.addEpisodes();
            seriesLibrary[seriesCount] = series;

            seriesCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Series Entries reached.");
    }


    //For displaying medi type entries:
    /**
     * displayBookEntry() displays the given book's details (title, author, genre, and no. of chapters)
     * @param book - the book whose detalls shall be displayed
     */
    public void displayBookEntry(Book book)
    {
        System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor());
        System.out.println("\tGenre: " + book.getGenre() + "\tNo. of Chapters: " + book.getChapterCount());
        System.out.println("");
    }

    /**
     * displayAlbumEntry() displays the given album's details (title, artist, genre, and no. of tracks)
     * @param album - the album whose detalls shall be displayed
     */
    public void displayAlbumEntry(Album album)
    {
        System.out.println("\"" + album.getTitle() + "\" by " + album.getArtist());
        System.out.println("\tGenre: " + album.getGenre() + "\tNo. of Tracks: " + album.getTrackCount());
        System.out.println("");
    }

    /**
     * displaySeriesEntry() displays the given series' details (title, genre, no. of seasons, and no. of episodes)
     * @param series - the series whose detalls shall be displayed
     */
    public void displaySeriesEntry(Series series)
    {
        System.out.println("\"" + series.getTitle() + "\"");
        System.out.println("\tGenre: " + series.getGenre() + "\tNo. of Seasons: " + series.getSeasonCount() + "\tNo. of Episodes: " + series.getEpisodeCount());
        System.out.println("");
    }


    //For displaying media type libraries:
    /**
     * displayBookLibrary() displays all entries in the book library
     */
    public void displayBookLibrary()
    {
        int i;

        System.out.println("BOOK LIBRARY");
        for(i=0; i<bookCount; i++)
        {
            System.out.print("[" + i+1 + "]");
            displayBookEntry(bookLibrary[i]);
        }
    }

    /**
     * displayAlbumLibrary() displays all entries in the album library
     */
    public void displayAlbumLibrary()
    {
        int i;

        System.out.println("ALBUM LIBRARY");
        for(i=0; i<albumCount; i++)
        {
            System.out.print("[" + i+1 + "]");
            displayAlbumEntry(albumLibrary[i]);
        }
    }

    /**
     * displaySeriesLibrary() displays all entries in the series library
     */
    public void displaySeriesLibrary()
    {
        int i;

        System.out.println("SERIES LIBRARY");
        for(i=0; i<seriesCount; i++)
        {
            System.out.print("[" + i+1 + "]");
            displaySeriesEntry(seriesLibrary[i]);
        }
    }


    //For removing entries:
    /**
     * removeBook() removes a book in the book library
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
            int j;
            for(j=index; j<bookCount-1; j++)
                bookLibrary[j] = bookLibrary[j+1];

            bookLibrary[j] = null;
            bookCount--;

            System.out.println("\"" + bookLibrary[index].getTitle() + "\" by " + bookLibrary[index].getAuthor() + "is removed.");
        } else
        System.out.println("Invalid Book Number. Exiting Book Removal...");
        
        sc.close();
    }

    /**
     * removeAlbum() removes an album in the album library
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
            int j;
            for(j=index; j<bookCount-1; j++)
                albumLibrary[j] = albumLibrary[j+1];

            albumLibrary[j] = null;
            albumCount--;

            System.out.println("\"" + albumLibrary[index].getTitle() + "\" by " + albumLibrary[index].getArtist() + "is removed.");
        } else
        System.out.println("Invalid Album Number. Exiting Album Removal...");
        
        sc.close();
    }

    /**
     * removeSeries() removes a series in the series library
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
            int j;
            for(j=index; j<bookCount-1; j++)
                seriesLibrary[j] = seriesLibrary[j+1];

            seriesLibrary[j] = null;
            seriesCount--;

            System.out.println("\"" + seriesLibrary[index].getTitle() + "is removed.");
        } else
        System.out.println("Invalid Series Number. Exiting Series Removal...");
        
        sc.close();
    }


    //For displaying all entries
    /**
      * displayLibrary() displays all the entries (books, albums, and series) without any filters
     */
    public void displayLibrary()
    {
        displayBookLibrary();   //Display all the book entries
        System.out.println("");
        displayAlbumLibrary();  //Display all the album entries
        System.out.println("");
        displaySeriesLibrary(); //Display all the series entries
    }

    /**
      * filterByStatus() displays all the entries (books, albums, and series) with the given status
      * @param status - the String containing the status condition (Planned, In Progress, or Completed)
     */
    public void filterByStatus(String status)
    {
        int i;

        System.out.println("BOOK LIBRARY [" + status + "]");    //Display all book entries with the given status
        for(i=0; i<bookCount; i++)
        {
            if(bookLibrary[i].getStatus().equals(status))
            {
                System.out.print("[" + i+1 + "] ");
                displayBookEntry(bookLibrary[i]);
            }
        }

        System.out.println("ALBUM LIBRARY [" + status + "]");    //Display all album entries with the given status
        for(i=0; i<albumCount; i++)
        {
            if(albumLibrary[i].getStatus().equals(status))
            {
                System.out.print("[" + i+1 + "] ");
                displayAlbumEntry(albumLibrary[i]);
            }
        }

        System.out.println("SERIES LIBRARY [" + status + "]");    //Display all series entries with the given status
        for(i=0; i < seriesCount; i++)
        {
            if(seriesLibrary[i].getStatus().equals(status))
            {
                System.out.print("[" + i+1 + "] ");
                displaySeriesEntry(seriesLibrary[i]);
            }
        }
    }

    /**
      * filterByType() displays all the entries (books, albums, and series) with the given media type
      * @param type - the String containing the medita type condition (Book, Album, or Series)
     */
    public void filterByType(String type)
    {
        if(type.equals("Book"))
            displayBookLibrary();
        else if(type.equals("Album"))
            displayAlbumLibrary();
        else if (type.equals("Series"))
            displaySeriesLibrary();
    }

    /**
      * filterByStatsAndType() displays all the entries (books, albums, and series) with the given status and media type
      * @param status - the String containing the status condition (Planned, In Progress, or Completed)
      * @param type - the String containing the medita type condition (Book, Album, or Series)
     */
    public void filterByType(String status, String type)
    {
        if(type.equals("Book"))
        {
            System.out.println("BOOK LIBRARY [" + status + "]");    //Display all book entries with the given status
            for(int i=0; i<bookCount; i++)
            {
                if(bookLibrary[i].getStatus().equals(status))
                {
                    System.out.print("[" + i+1 + "] ");
                    displayBookEntry(bookLibrary[i]);
                }
            }
        } else if(type.equals("Album"))
        {
            System.out.println("ALBUM LIBRARY [" + status + "]");    //Display all album entries with the given status
            for(int i=0; i<albumCount; i++)
            {
                if(albumLibrary[i].getStatus().equals(status))
                {
                    System.out.print("[" + i+1 + "] ");
                    displayAlbumEntry(albumLibrary[i]);
                }
            }
        } else if (type.equals("Series"))
        {
            System.out.println("SERIES LIBRARY [" + status + "]");    //Display all series entries with the given status
            for(int i=0; i < seriesCount; i++)
            {
                if(seriesLibrary[i].getStatus().equals(status))
                {
                    System.out.print("[" + i+1 + "] ");
                    displaySeriesEntry(seriesLibrary[i]);
                }
            }
        }
    }

    //For summary
    /**
      * summary() displays the library's:
      *     1. total entry count per media type
      *     2. total entry count of all media types
      *     3. total entry count per status
      *     4. average rating of completed entries
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

        System.out.println("\n\tTotal Entries in Your Library: " + bookCount+albumCount+seriesCount);   //Display total entries in library
        System.out.println("");

        for(i=0; i<bookCount; i++)  //Get status counts for book entries
        {
            if(bookLibrary[i].getStatus().equals("Planned"))
                plannedCount++;
            if(bookLibrary[i].getStatus().equals("In Progress"))
                inProgressCount++;
            if(bookLibrary[i].getStatus().equals("Completed"))
            {
                completedBooks[completedBookCount] = bookLibrary[i];
                completedBookCount++;
            }
        }

        for(i=0; i<albumCount; i++)  //Get status counts for album entries
        {
            if(albumLibrary[i].getStatus().equals("Planned"))
                plannedCount++;
            if(albumLibrary[i].getStatus().equals("In Progress"))
                inProgressCount++;
            if(albumLibrary[i].getStatus().equals("Completed"))
            {
                completedAlbums[completedAlbumCount] = albumLibrary[i];
                completedAlbumCount++;
            }
        }

        for(i=0; i<seriesCount; i++)  //Get status counts for series entries
        {
            if(seriesLibrary[i].getStatus().equals("Planned"))
                plannedCount++;
            if(seriesLibrary[i].getStatus().equals("In Progress"))
                inProgressCount++;
            if(seriesLibrary[i].getStatus().equals("Completed"))
            {
                completedSeries[completedSeriesCount] = seriesLibrary[i];
                completedSeriesCount++;
            }
        }

        System.out.println("Total Entries Planned in Your Library: " + plannedCount);    //Display total book in library
        System.out.println("Total Entries In Progress in Your Library: " + inProgressCount);  //Display total albums in library
        System.out.println("Total Entries Completed in Your Library: " + completedBookCount+completedAlbumCount+completedSeriesCount); //Display total series in library
        System.out.println("");

        //Compute for average book rating
        for(i=0; i<completedBookCount; i++)
            aveBookRating += completedBooks[i].getRating();
        aveBookRating /= (completedBookCount * 1.0);

        //Compute for average album rating
        for(i=0; i<completedAlbumCount; i++)
            aveAlbumRating += completedAlbums[i].getRating();
        aveAlbumRating /= (completedAlbumCount * 1.0);

        //Compute for average series rating
        for(i=0; i<completedSeriesCount; i++)
            aveSeriesRating += completedSeries[i].getRating();
        aveSeriesRating /= (completedSeriesCount * 1.0);

        System.out.println("Your Average Book Rating: " + aveBookRating);       //Display average book rating
        System.out.println("Your Average Album Rating: " + aveAlbumRating);     //Display average album rating
        System.out.println("Your Average Series Rating: " + aveSeriesRating);   //Display average series rating

        System.out.println("\n\tYour Total Average Rating: " + (aveBookRating*completedBookCount + aveAlbumRating*completedAlbumCount + aveSeriesRating*completedSeriesCount)/(completedBookCount+completedAlbumCount+completedSeriesCount));   //Display average rating of all entries
    }


    //GETTERS
    public int getBookCount()
    {
        return bookCount;
    }

    public int getAlbumCount()
    {
        return albumCount;
    }

    public int getSeriesCount()
    {
        return seriesCount;
    }

    public Book getBookEntry(int index)
    {
        return bookLibrary[index];
    }

    public Album getAlbumEntry(int index)
    {
        return albumLibrary[index];
    }

    public Series getSeriesEntry(int index)
    {
        return seriesLibrary[index];
    }
}
