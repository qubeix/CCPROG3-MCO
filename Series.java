import java.util.*;

/**
  * The class <code>Series</code> contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of a series entry with:<br>
  * the common methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and<br>
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
    /**
      * Accepts a title, genre, and count of seasons as parameters, and initializes status to "Plenned," rating to 0, review to empty, current season to 1, and current episode to 0, and list of episode counts 
      * @param title the title of the series
      * @param genre the genre of the series
      * @param seasonCount the count of seasons in the series
     */
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
        currentSeason = 1;
        currentEpisode = 0;
    }

    //METHODS
    /**
      * Assigns a status ("Planned", "In Progress", or "Completed") to an entry
      * @param status the status to be assigned
     */
    public void addStatus(String status)
    {
        if(status.equalsIgnoreCase("Planned") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }
    
    /**
      * Modifies an entry's status ("Planned", "In Progress", or "Completed")
      * @param newStatus the status to be newly assigned
     */
    public void updateStatus(String newStatus)
    {
        if(newStatus.equalsIgnoreCase("Planned") || newStatus.equalsIgnoreCase("In Progress") || newStatus.equalsIgnoreCase("Completed"))  //If the new status is valid
            this.status = newStatus;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
     
        if(status.equalsIgnoreCase("Completed"))
        {
            currentEpisode = episodeCount[SEASONCOUNT-1];
            currentSeason = SEASONCOUNT;
        }
    }

    /**
      * Assigns a rating to an entry
      * @param rating the number rating to be assigned
      * @pre. the entry must have a status of "Completed" to assign a rating
     */
    public void addRating(int rating)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * Assigns a review to an entry
      * @param review the review to be assigned
      * @pre. the entry must have a status of "Completed" to assign a review
     */
    public void addReview(String review)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * Assigns a number of episodes per season
     */
    public void addEpisodes()
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<SEASONCOUNT; i++)
        {
            do
            {
                System.out.print("Season " + (i+1) + " Episode Count: ");
                episodeCount[i] = sc.nextInt();
            } while(episodeCount[i] <= 0);
        }
    }

    /**
      * Moves the current episode and current season forward by one as necessary
     */
    public void nextEpisode()
    {
        if(currentEpisode < episodeCount[currentSeason-1])   //If the current episode has not reached the last episode
        {
            currentEpisode++;                   //Move to the next episode

            if(status.equalsIgnoreCase("Planned"))           //If the status if "Planned"
                updateStatus("In Progress");       //Update the status to "In Progress"
            
            if(currentEpisode == episodeCount[SEASONCOUNT-1] && currentSeason == SEASONCOUNT)     //If it is the last episode and the last season
                updateStatus("Completed");                        //Update the status to "Completed"

        } else if(currentEpisode == episodeCount[currentSeason-1] && currentSeason != SEASONCOUNT)
        {
            currentEpisode = 1;
            currentSeason++;
        }
        else                                                                //If the series is completed
            System.out.println("You have already completed this series");    //Display a message
    }

    //GETTERS
    /**
   	  * Returns the status of the series
   	  * @return the status
   	 */
    public String getStatus()
    {
        return status;
    }

    /**
   	  * Returns the rating of the series
   	  * @return the rating
   	 */
    public int getRating()
    {
        return rating;
    }

    /**
   	  * Returns the review of the series
   	  * @return the review
   	 */
    public String getReview()
    {
        return review;
    }

    /**
   	  * Returns the title of the series
   	  * @return the title
   	 */
    public String getTitle()
    {
        return TITLE;
    }

    /**
   	  * Returns the genre of the series
   	  * @return the genre
   	 */
    public String getGenre()
    {
        return GENRE;
    }

    /**
   	  * Returns the number of seasons in the series
   	  * @return the number of seasons
   	 */
    public int getSeasonCount()
    {
        return SEASONCOUNT;
    }

    /**
   	  * Returns the number of episodes of a given season in the series
      * @param season the season of the episodes
   	  * @return the number of episodes of a given season
   	 */
    public int getEpisodeCount(int season)
    {
        return episodeCount[season-1];
    }

    /**
   	  * Returns the number of episodes in the series
   	  * @return the number of episodes
   	 */
    public int getEpisodeCount(){
        int total = 0;
        for (int i = 0; i < SEASONCOUNT; i++){
            total += episodeCount[i];
        }
        return total; 
    }

    /**
   	  * Returns the progress of the series, in the format of: "{@literal Season <current season>: <current episode>/<total episodes in current season> Episodes}"
   	  * @return the progress
   	 */
    public String getProgress()
    {
        return "Season " + currentSeason + ": " + currentEpisode + "/" + episodeCount[currentSeason-1] + " Episodes\n";   //return the season and amount of episodes watched
    }
}
