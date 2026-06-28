import java.util.*;

public class Series
{
    //Variables
    private String status;
    private int rating;
    private String review;

    private final String TITLE;
    private final String GENRE;

    private final int SEASONCOUNT;     //the number of seasons
    private int[] episodeCount;        //stores the number of episodes for each season
    //private final int EPISODECOUNT;    //the number of episodes
    private int currentSeason;         //the user's current season
    private int currentEpisode;        //the user's current episode

    //Constructor
    public Series(String title, String genre, int seasonCount)
    {
        TITLE = title;
        GENRE = genre;

        SEASONCOUNT = seasonCount;
        //EPISODECOUNT = episodeCount;

        episodeCount = new int[SEASONCOUNT];

        //Initialization
        status = "Planned";
        rating = 0;
        review = "";
        currentSeason = 0;
        currentEpisode = 0;
    }

    //Methods
    public void addStatus(String status)
    {
        if(status.equals("Planned") || status.equals("In Progress") || status.equals("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    public void updateStatus(String newStatus)
    {
        if(newStatus.equals("Planned") || newStatus.equals("In Progress") || newStatus.equals("Completed"))  //If the new status is valid
            this.status = newStatus;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    public void addRating(int rating)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    public void addReview(String review)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    public void addEpisodes()
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<SEASONCOUNT; i++)
        {
            do
            {
                System.out.print("Season " + i+1 + " Episode Count: ");
                episodeCount[i] = sc.nextInt();
            } while(episodeCount[i] <= 0);
        }

        sc.close();
    }

    public void nextEpisode()
    {
        if(currentEpisode < episodeCount[currentSeason-1])   //If the current episode has not reached the last episode
        {
            currentEpisode++;                   //Move to the next episode

            if(status.equals("Planned"))           //If the status if "Planned"
                updateStatus("In Progress");       //Update the status to "In Progress"
            
            if(currentEpisode == episodeCount[SEASONCOUNT-1] && currentSeason == SEASONCOUNT)     //If it is the last episode and the last season
                updateStatus("Completed");                        //Update the status to "Completed"

        } else if(currentEpisode == episodeCount[currentSeason-1])
        {
            currentEpisode = 1;
            currentSeason++;
        }
        else                                                                //If the series is completed
            System.out.println("You have already completed this series");    //Display a message
    }

    //Getters
    public String getStatus()
    {
        return status;
    }

    public int getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }

    public String getTitle()
    {
        return TITLE;
    }

    public String getGenre()
    {
        return GENRE;
    }

    public int getSeasonCount()
    {
        return SEASONCOUNT;
    }

    public int getEpisodeCount(int season)
    {
        return episodeCount[season-1];
    }

    public String getProgress()
    {
        return "Season " + currentSeason + ": " + currentEpisode + "/" + episodeCount[currentSeason-1] + " Episodes";   //return the season and amount of episodes watched
    }
}
