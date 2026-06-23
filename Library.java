import java.util.*;

public class Library
{
    private Book[] book;
    private Album[] album;
    private Series[] series;
    private int bookCount;
    private int albumCount;
    private int seriesCount;
    private final int MAXCOUNT = 100;   //Maximum count of media entries

    //Constructor
    public Library()
    {
        book = new Book[MAXCOUNT];
        album = new Album[MAXCOUNT];
        series = new Series[MAXCOUNT];

        bookCount = 0;
        albumCount = 0;
        seriesCount = 0;
    }

    //Methods
    public void addEntry(Book book)
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

            book = new Book(title, author, genre, chapterCount);

            bookCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Book Entries reached.");
    }

    public void addEntry(Album album)
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

            album = new Album(title, artist, genre, trackCount);

            albumCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Album Entries reached.");
    }

    public void addEntry(Series series)
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

            series = new Series(title, genre, seasonCount);
            series.addEpisodes();

            seriesCount++;

            sc.close();
        } else      //If there are no more slots for an entry
            System.out.println("Maximum Series Entries reached.");
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
