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

    //Constructor
    public Library()
    {
        bookLibrary = new Book[MAXCOUNT];
        albumLibrary = new Album[MAXCOUNT];
        seriesLibrary = new Series[MAXCOUNT];

        bookCount = 0;
        albumCount = 0;
        seriesCount = 0;
    }

    //Methods
    //For adding entries:
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

            Seriess series = new Series(title, genre, seasonCount);
            series.addEpisodes();
            seriesLibrary[seriesCount] = series;

            seriesCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Series Entries reached.");
    }


    //For displaying medi type entries:
    public void displayBookEntry(Book book)
    {
        System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor());
        System.out.println("\tGenre: " + book.getGenre() + "\tNo. of Chapters: " + book.getChapterCount());
        System.out.println("");
    }

    public void displayAlbumEntry(Album album)
    {
        System.out.println("\"" + album.getTitle() + "\" by " + album.getArtist());
        System.out.println("\tGenre: " + album.getGenre() + "\tNo. of Tracks: " + album.getTrackCount());
        System.out.println("");
    }

    public void displaySeriesEntry(Series series)
    {
        System.out.println("\"" + series.getTitle() + "\"");
        System.out.println("\tGenre: " + series.getGenre() + "\tNo. of Seasons: " + series.getSeasonCount() + "\tNo. of Episodes: " + series.getEpisodeCount());
        System.out.println("");
    }


    //For displaying media type libraries:
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
    public void removeBook()
    {
        int index;
        displayBookLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Book Number to Remove: ");
        index = nextInt() - 1;

        if(index >= 0)
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

    public void removeAlbum()
    {
        int index;
        displayAlbumLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Album Number to Remove: ");
        index = nextInt() - 1;

        if(index >= 0)
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

    public void removeSeries()
    {
        int index;
        displaySeriesLibrary();

        Scanner sc = new Scanner(System.in);
        System.out.print("Series Number to Remove: ");
        index = nextInt() - 1;

        if(index >= 0)
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
    displayLibrary() displays all the entries (books, albums, and series) without any filters
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
    filterByStatus() displays all the entries (books, albums, and series) with the given status
    @param status - the String containing the status condition (Planned, In Progress, or Completed)
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
        for(i=0; iserieskCount; i++)
        {
            if(seriesLibrary[i].getStatus().equals(status))
            {
                System.out.print("[" + i+1 + "] ");
                displaySeriesEntry(seriesLibrary[i]);
            }
        }
    }



    //Getters
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
}
