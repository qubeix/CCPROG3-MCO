import java.util.*;

/**
  * The Series class contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of a series entry with:
  * the common methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and
  * the unique methods of: assigning the number of episodes per season, and updating the series entry's current episode and season progress
 */
public class Series
{
    //ATTRIBUTES
    private String status;
    private int rating;
    private String review;

    private final String TITLE;
    private final String GENRE;

    private final int SEASONCOUNT;     //the number of seasons
    private int[] episodeCount;        //stores the number of episodes for each season
    private int currentSeason;         //the user's current season
    private int currentEpisode;        //the user's current episode

    //CONSTRUCTOR
    public Series(String title, String genre, int seasonCount)
    {
        TITLE = title;
        GENRE = genre;

        SEASONCOUNT = seasonCount;

        episodeCount = new int[SEASONCOUNT];

        //Initialization
        status = "Planned";
        rating = 0;
        review = "";
        currentSeason = 0;
        currentEpisode = 0;
    }

    //METHODS
    /**
      * addStatus() assigns a status ("Planned", "In Progress", or "Completed") to an entry
      * @param status - the status to be assigned
     */
    public void addStatus(String status)
    {
        if(status.equals("Planned") || status.equals("In Progress") || status.equals("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }
    
    /**
      * updateStatus() modifies an entry's status ("Planned", "In Progress", or "Completed")
      * @param newStatus - the status to be newly assigned
     */
    public void updateStatus(String newStatus)
    {
        if(newStatus.equals("Planned") || newStatus.equals("In Progress") || newStatus.equals("Completed"))  //If the new status is valid
            this.status = newStatus;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    /**
      * addRating() assigns a rating to an entry
      * @param rating - the number rating to be assigned
      * @pre - the entry must have a status of "Completed" to assign a rating
     */
    public void addRating(int rating)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    /**
      * addReview() assigns a review to an entry
      * @param review - the review to be assigned
      * @pre - the entry must have a status of "Completed" to assign a review
     */
    public void addReview(String review)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    /**
      * addEpisodes() assigns a number of episodes per season
     */
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

    /**
      * nextEpisode() moves the current episode and current season forward by one as necessary
     */
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

    //GETTERS
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

    public int getEpisodeCount(){
        int total = 0;
        for (int i = 0; i < SEASONCOUNT; i++){
            total += episodeCount[i];
        }
        return total; 
    }

    public String getProgress()
    {
        return "Season " + currentSeason + ": " + currentEpisode + "/" + episodeCount[currentSeason-1] + " Episodes";   //return the season and amount of episodes watched
    }
}
