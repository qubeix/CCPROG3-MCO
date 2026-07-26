package com.example.model;
import java.util.*;

/**
  * The class <code>Series</code> contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of a series entry with:<br>
  * the inherited methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and<br>
  * the unique methods of: assigning the number of episodes per season, updating the series entry's current episode and season progress, and returning its information via String
 */
public class Series extends MediaEntry
{
    //ATTRIBUTES
    private final String STUDIO;
    private final int SEASONCOUNT;     //the number of seasons
    private int[] episodeCount;        //stores the number of episodes for each season
    private int currentSeason;         //the user's current season
    private int currentEpisode;        //the user's current episode

    //CONSTRUCTOR
    /**
      * Accepts a title, genre, and count of seasons as parameters, and initializes status to "Planned," rating to 0, review to empty, current season to 1, and current episode to 0, and list of episode counts 
      * @param title the title of the series
      * @param genre the genre of the series
      * @param seasonCount the count of seasons in the series
     */
    public Series(String title, String studio, String genre, int seasonCount)
    {
        super(title, genre);

        STUDIO = studio;
        SEASONCOUNT = seasonCount;
        episodeCount = new int[SEASONCOUNT];

        //Initialization
        currentSeason = 1;
        currentEpisode = 0;
    }

    //METHODS
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
    public void updateProgress()
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

    /**
     * Returns a String containing the information of the series (title, genre, seasons, and episodes (and if the entry is completed, rating and review))
     */
    public String toString()
    {
        String info = "\"" + TITLE + "\" by " + STUDIO;
        info += "\tGenre: " + GENRE + "  |  No. of Seasons: " + SEASONCOUNT + "  |  No. of Episodes: " + getEpisodeCount();
        if(status.equalsIgnoreCase("Completed"))
        {
            info += "\tRating: " + rating;
            info += "\tReview: " + review;
        }
        info += "\n";

        return info;
    }

    //GETTERS
    /**
	     * Returns the studio of the series
	     * @return the studio
	    */
    public String getStudio()
    {
        return STUDIO;
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
