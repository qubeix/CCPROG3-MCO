/**
  * The class <code>Album</code> contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of an album entry with: <br>
  * the common methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and <br>
  * the unique method of: updating the album entry's current track progress
 */
public class Album
{
	//ATTRIBUTES
	private String status;
	private int rating;
	private String review;
	private int currentTrack; 
	
	private final String TITLE;
	private final String ARTIST;
	private final String GENRE;
	private final int TRACKCOUNT;
	
	
	//CONSTRUCTOR
	public Album(String title, String artist, String genre, int trackCount)
	{
		TITLE = title;
		ARTIST = artist;
		GENRE = genre;
		TRACKCOUNT = trackCount; 
		
		//initialization
		this.status = "Planned";
		this.rating = 0;
		this.review = " ";
		this.currentTrack = 0;
	}
	
	//METHODS
    /**
      * Assigns a status ("Planned", "In Progress", or "Completed") to an entry
      * @param status the status to be assigned
     */
	public void addStatus(String status)
	{
		if(status.equalsIgnoreCase("Planned") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Completed")){
			this.status = status;
		}
		else{
			System.out.println("Invalid Status");
		}

		if(status.equalsIgnoreCase("Completed"))
            currentTrack = TRACKCOUNT;
	}
	
	/**
      * Modifies an entry's status ("Planned", "In Progress", or "Completed")
      * @param newStatus the status to be newly assigned
     */
	public void updateStatus(String newStatus)
	{
		if (newStatus.equalsIgnoreCase("Planned") || newStatus.equalsIgnoreCase("In Progress") || newStatus.equalsIgnoreCase("Completed"))
		{
			this.status = newStatus;
		}
		else
		{
			System.out.println("Invalid");
		}
	}
	
	/**
      * Assigns a rating to an entry
      * @param rating the number rating to be assigned
      * @pre the entry must have a status of "Completed" to assign a rating
     */
	public void addRating(int rating)
	{
		if (status.equalsIgnoreCase("Completed"))
		{
			this.rating = rating;
		}
		else
		{
			System.out.println("This entry is yet to be completed\n");
		}
	}
	
	/**
      * Assigns a review to an entry
      * @param review the review to be assigned
      * @pre the entry must have a status of "Completed" to assign a review
     */
	public void addReview(String review)
	{
		if (status.equalsIgnoreCase("Completed"))
		{
			this.review = review;
		}
		else
		{
			System.out.println("This entry is yet to be completed\n");
		}
	}
	
	/**
      * Moves the current track forward by one
     */
	public void nextTrack()
	{
		if (currentTrack < TRACKCOUNT)
		{
			currentTrack++;
			System.out.println("Now playing track " + currentTrack + " of " + TRACKCOUNT);
			
			if(status.equalsIgnoreCase("Planned")){
				status = "In Progress";
			}

			if (currentTrack == TRACKCOUNT)
			{
				status = "Completed";
				System.out.println("Album completed");
			}
		}
		else
		{
			System.out.println("All tracks are completed");
		}
		
	}
	
	//GETTERS
	/**
	  * Returns the title of the album
	  * @return the title
	*/
	public String getTitle(){
		return TITLE;
	}

	/**
	  * Returns the artist of the album
	  * @return the artist
	*/
	public String getArtist(){
		return ARTIST;
	}

	/**
	  * Returns the genre of the album
	  * @return the genre
	*/
	public String getGenre(){
		return GENRE;
	}

	/**
	  * Returns the number of tracks in the album
	  * @return the number of tracks
	*/
	public int getTrackCount(){
		return TRACKCOUNT;
	}

	/**
	  * Returns the status of the album
	  * @return the status
	*/
	public String getStatus()
	{
		return status;
	}

	/**
	  * Returns the rating of the album
	  * @return the rating
	*/
	public int getRating()
	{
		return rating;
	}

	/**
	  * Returns the review of the album
	  * @return the review
	*/
	public String getReview()
	{
		return review;
	}

	/**
	  * Returns the progress of the album, in the format of: "Track <current track> of <total tracks>"
	  * @return the progress
	*/
	public String getProgress()
	{
		return "Track " +currentTrack+ " of " +TRACKCOUNT+ "\n";
	}
}
