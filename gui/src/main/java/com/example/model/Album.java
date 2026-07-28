package com.example.model;

/**
 * The class <code>Album</code> contains the attributes, constructor, methods,
 * and getters necessary for the creation, modification, and manipulation of an
 * album entry with: <br>
 * the inherited methods of: assigning a status (and modifying it), assigning a
 * rating, and assigning a review; and <br>
 * the unique method of: updating the album entry's current track progress, and
 * returning its information via String
 */

public class Album extends MediaEntry {
	// ATTRIBUTES
	private final String ARTIST;
	private final int TRACKCOUNT;
	private int currentTrack;

	// CONSTRUCTOR
	/**
	 * Accepts a title, artist, genre, and count of tracks as parameters, and
	 * initializes status to "Planned," rating to 0, review to empty, current track
	 * to 0
	 * 
	 * @param title      the title of the album
	 * @param artist     the artist of the album
	 * @param genre      the genre of the album
	 * @param trackCount the count of tracks in the album
	 */
	public Album(String title, String artist, String genre, int trackCount) {
		super(title, genre);
		ARTIST = artist;
		TRACKCOUNT = trackCount;
		currentTrack = 0;
	}

	// METHODS
	/**
	 * Moves the current track forward by one
	 */
	public void updateProgress() {
		if (currentTrack < TRACKCOUNT) {
			currentTrack++;
			System.out.println("Now playing track " + currentTrack + " of " + TRACKCOUNT);

			if (status.equalsIgnoreCase("Planned")) {
				status = "In Progress";
			}

			if (currentTrack == TRACKCOUNT) {
				status = "Completed";
				System.out.println("Album completed");
			}
		} else {
			System.out.println("All tracks are completed");
		}
	}

	/**
	 * Returns a String containing the information of the album (title, artist,
	 * genre, and tracks (and if the entry is completed, rating and review))
	 */
	public String toString() {
		String info = "\"" + TITLE + "\" by " + ARTIST;
		info += "\tGenre: " + GENRE + "  |  No. of Tracks: " + TRACKCOUNT;
		if (status.equalsIgnoreCase("Completed")) {
			info += "\tRating: " + rating;
			info += "\tReview: " + review;
		}
		info += "\n";

		return info;
	}

	// GETTERS
	/**
	 * Returns the artist of the album
	 * 
	 * @return the artist
	 */
	public String getArtist() {
		return ARTIST;
	}

	/**
	 * Returns the number of tracks in the album
	 * 
	 * @return the number of tracks
	 */
	public int getTrackCount() {
		return TRACKCOUNT;
	}

	/**
	 * Returns the progress of the album, in the format of: "Track
	 * {@literal<current track>} of {@literal<total tracks>}"
	 * 
	 * @return the progress
	 */
	public String getProgress() {
		return "Track " + currentTrack + " of " + TRACKCOUNT + "\n";
	}

}