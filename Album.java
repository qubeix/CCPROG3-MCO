import java.util.*;

public class Album
{
	//Variables
	private String status;
	private int rating;
	private String review;
	private int currentTrack; 
	
	private final String TITLE;
	private final String ARTIST;
	private final String GENRE;
	private final int TRACKCOUNT;
	
	
	//Constructor
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
	
	//Methods 
	public void addStatus()
	{
		
	}
	
	public void updateStatus(String newStatus)
	{
		if (newStatus.equals("Planned") || newStatus.equals("In Progress") || newStatus.equals("Completed"))
		{
			this.status = newStatus;
		}
		else
		{
			System.out.println("Invalid");
		}
	}
	
	public void addRating(int rating)
	{
		if (status.equals("Completed"))
		{
			this.rating = rating;
		}
		else
		{
			System.out.println("Allowed for completed entries");
		}
	}
	
	public void addReview(String review)
	{
		if (status.equals("Completed"))
		{
			this.review = review;
		}
		else
		{
			System.out.println("Allowed for completed entries");
		}
	}
	
	public void nextTrack()
	{
		if (currentTrack < TRACKCOUNT)
		{
			currentTrack++;
			System.out.println("Now playing track " + currentTrack + " of " + TRACKCOUNT);
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
	
	public String getProgress()
	{
		return "Track" +currentTrack+ " of " +TRACKCOUNT;
	}
}
