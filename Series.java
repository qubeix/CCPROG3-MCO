import java.util.*;

public class Series
{
    //Variables
    private String status;
    private int rating;
    private String review;

    private final String TITLE;
    private final String GENRE;

    private int seasonCount;     //the number of seasons
    private int episodeCount;    //the number of episodes
    private int currentSeason;         //the user's current season
    private int currentEpisode;        //the user's current episode

    //Constructor
    public Series(String title, String genre, int seasonCount, int episodeCount)
    {
        TITLE = title;
        GENRE = genre;

        if(seasonCount > 0)
            this.seasonCount = seasonCount;
        else
            System.out.println("Invalid Seasons");

        if(episodeCount > 0)
            this.episodeCount = episodeCount;
        else
            System.out.println("Invalid Episodes");

        //Initialization
        status = "Planned";
        rating = 0;
        review = "";
        currentSeason = 0;
        currentEpisode = 0;
    }
}
