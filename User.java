package com.example.model;
import java.util.*;

/**
 * The class <code>User</code> contains the attributes, constructor, methods, and getters necessary for the creation, validation, and actions of a user, such as<br>
 * the methods of: logging in, logging out, adding entries, removing entries, viewing libraries, and getting their library summary
 */

public class User
{
	//ATTRIBUTES 
	private String username;
	private String password;
	private String name;
	private Library library; 
	
	//CONSTRUCTORS
	/**
      * Accepts a username, password, and name as parameters, and initializes them as the user's username, password, and name, respectively. A Library is also instantiated. 
      * @param username the username of the user
      * @param password the password of the user
      * @param name the name of the user
     */
	public User (String username, String password, String name)
	{
		this.username = username;
		this.password = password;
		this.name = name;
		this.library = new Library();
	}

	//METHODS
	/**
	 * Logs a user into their account
	 * @pre. user exists
	 * @param inputUsername the username provided by the user in logging in
	 * @param inputPassword the password provided by the user in logging in
	 * @return true if the inputted password matches the password of the inputted username, false otherwise
	 */
	public boolean login (String inputUsername, String inputPassword) 
	{
		if (inputUsername.equals(username) && inputPassword.equals(password))
		{
			System.out.println("Login Successful! Welcome, " +name+ "!\n");
			return true;
		}
		else
		{
			System.out.println("Invalid username or password");
			return false; 
		}
	}
	
	/**
	 * Logs a user out of their account
	 * @pre. user has been logged in
	 */ 
	public void logout()
	{
		System.out.println("Thank you! " +name+ " has logged out\n");
	}

	/**
	 * Adds a book entry into the user's library
	 */
	public void addBookEntry(){
		library.addBook();
		System.out.println("Book added to library\n");
	}

	/**
	 * Adds an album entry into the user's library
	 */
	public void addAlbumEntry(){
		library.addAlbum();
		System.out.println("Album added to library\n");
	}

	/**
	 * Adds a series entry into the user's library
	 */
	public void addSeriesEntry(){
		library.addSeries();
		System.out.println("Series added to library\n");
	}
	
	/**
	 * Removes a book entry from the user's library
	 * @pre. book entry exists in library
	 */
	public void removeBookEntry(){
		library.removeBook();
	}
	
	/**
	 * Removes an album entry from the user's library
	 * @pre. album entry exists in library
	 */
	public void removeAlbumEntry(){
		library.removeAlbum();
	}
	
	/**
	 * Removes a series entry from the user's library
	 * @pre. series entry exists in library
	 */
	public void removeSeriesEntry(){
		library.removeSeries();
	}

	/**
	 * Displays the user's libraries (book, album, and series)
	 */ 
	public void viewLibrary()
	{
		library.displayLibrary();
	}
	
	/**
	 * Displays the user's libraries of a given status (Planned, In Progress, or Completed)
	 */ 
	public void viewByStatus(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter status (Planned, In Progress, Completed): ");
		String status = sc.nextLine();
		System.out.println("");
		library.filterByStatus(status);
	}
	
	/**
	 * Displays the user's library of a given media type (Book, Album, or Series)
	 */ 
	public void viewByType(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter type (Book, Album, Series): ");
		String type = sc.nextLine();
		System.out.println("");
		library.filterByType(type);
	}

	/**
	 * Displays the user's library of a given status (Planned, In Progress, or Completed) and given media type (Book, Album, or Series)
	 */
	public void viewByStatusType(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter status (Planned, In Progress, Completed): ");
		String status = sc.nextLine();
		System.out.print("Enter type (Book, Album, Series): ");
		String type = sc.nextLine();
		System.out.println("");
		library.filterByStatusType(status, type);
	}

	/**
	 * Displays the user's library summary
	 */ 
	public void getSummary()
	{
		library.summary();
	}

	/**
	 * Rates a book entry in the user's library
	 */ 
	public void rateBookEntry(){
		if(library.getBookCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayBookLibrary();
			System.out.print("Book Number to Rate: ");
			int index = sc.nextInt() - 1;

			if(index >= 0 && index < library.getBookCount()){
				Book book = library.getBookEntry(index);

				int rating;
				do{
					System.out.print("Rating (1-10): ");
					rating = sc.nextInt();
					if(rating < 1 || rating > 10)
						System.out.println("Invalid Rating\n");
				} while(rating < 1 || rating > 10);

				book.addRating(rating);
			}
			else{
				System.out.println("Invalid Book Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Rates an album entry in the user's library
	 */
	public void rateAlbumEntry(){
		if(library.getAlbumCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayAlbumLibrary();
			System.out.print("Album Number to Rate: ");
			int index = sc.nextInt() - 1;

			if(index >= 0 && index < library.getAlbumCount()){
				Album album = library.getAlbumEntry(index);

				int rating;
				do{
					System.out.print("Rating (1-10): ");
					rating = sc.nextInt();
					if(rating < 1 || rating > 10)
						System.out.println("Invalid Rating\n");
				} while(rating < 1 || rating > 10);

				album.addRating(rating);
			}
			else{
				System.out.println("Invalid Album Number");
			}
			System.out.println("");
		}

		
	}

	/**
	 * Rates a series entry in the user's library
	 */
	public void rateSeriesEntry(){
		if(library.getSeriesCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displaySeriesLibrary();
			System.out.print("Series Number to Rate: ");
			int index = sc.nextInt() - 1;

			if(index >= 0 && index < library.getSeriesCount()){
				Series series = library.getSeriesEntry(index);

			int rating;
				do{
					System.out.print("Rating (1-10): ");
					rating = sc.nextInt();
					if(rating < 1 || rating > 10)
						System.out.println("Invalid Rating\n");
				} while(rating < 1 || rating > 10);

				series.addRating(rating);
			}
			else{
				System.out.println("Invalid Series Number");
			}
			System.out.println("");
		}
		
		
	}

	/**
	 * Reviews a book entry in the user's library
	 */
	public void reviewBookEntry(){
		if(library.getBookCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayBookLibrary();
			System.out.print("Book Number to Review: ");
			int index = sc.nextInt() - 1;
			System.out.print("Review: ");
			sc.nextLine();

			if(index >= 0 && index < library.getBookCount()){
				Book book = library.getBookEntry(index);
				String review = sc.nextLine();
				book.addReview(review);
			}
			else{
				System.out.println("Invalid Book Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Reviews an album entry in the user's library
	 */
	public void reviewAlbumEntry(){
		if(library.getAlbumCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayAlbumLibrary();
			System.out.print("Album to Review: ");
			int index = sc.nextInt() - 1;
			System.out.print("Review: ");
			sc.nextLine();

			if(index >= 0 && index < library.getAlbumCount()){
				Album album = library.getAlbumEntry(index);
				String review = sc.nextLine();
				album.addReview(review);
			}
			else{
				System.out.println("Invalid Album Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Reviews a series entry in the user's library
	 */
	public void reviewSeriesEntry(){
		if(library.getSeriesCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displaySeriesLibrary();
			System.out.print("Series to Review: ");
			int index = sc.nextInt() - 1;
			System.out.print("Review: ");
			sc.nextLine();

			if(index >= 0 && index < library.getSeriesCount()){
				Series series = library.getSeriesEntry(index);
				String review = sc.nextLine();
				series.addReview(review);
			}
			else{
				System.out.println("Invalid Series Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Updates a book entry's status in the user's library
	 */
	public void updateBookStatus(){
		if(library.getBookCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayBookLibrary();
			System.out.print("Book Number to Update: ");
			int index = sc.nextInt() - 1;
			sc.nextLine();

			if(index >= 0 && index < library.getBookCount()){
				Book book = library.getBookEntry(index);
				System.out.print("New Status (Planned, In Progress, Completed): ");
				String newStatus = sc.nextLine();
				book.updateStatus(newStatus);
			}
			else{
				System.out.println("Invalid Book Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Updates an album entry's status in the user's library
	 */
	public void updateAlbumStatus(){
		if(library.getAlbumCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayAlbumLibrary();
			System.out.print("Album Number to Update: ");
			int index = sc.nextInt() - 1;
			sc.nextLine();

			if(index >= 0 && index < library.getAlbumCount()){
				Album album = library.getAlbumEntry(index);
				System.out.print("New Status (Planned, In Progress, Completed): ");
				String newStatus = sc.nextLine();
				album.updateStatus(newStatus);
			}
			else{
				System.out.println("Invalid Album Number");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Updates a series entry's status in the user's library
	 */
	public void updateSeriesStatus(){
		if(library.getSeriesCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displaySeriesLibrary();
			System.out.print("Series Number to Update: ");
			int index = sc.nextInt() - 1;
			sc.nextLine();

			if(index >= 0 && index < library.getSeriesCount()){
				Series series = library.getSeriesEntry(index);
				System.out.print("New Status (Planned, In Progress, Completed): ");
				String newStatus = sc.nextLine();
				series.updateStatus(newStatus);
			}
			else{
				System.out.println("Invalid Series Number");
			}
			System.out.println("");
		}
	}

	/**
	 * Moves a book entry's chapter in the user's library
	 */
	public void moveBookChapter(){
		if(library.getBookCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayBookLibrary();
			System.out.print("Book Number to update Chapter: ");
			int index = sc.nextInt() - 1;
			System.out.println("");

			if(index >= 0 && index < library.getBookCount()){
				Book book = library.getBookEntry(index);
				book.updateProgress();
				System.out.println(book.getProgress());
			}
			else{
				System.out.println("Invalid Book Number");
			}
		}
	}

	/**
	 * Moves an album entry's track in the user's library
	 */
	public void moveAlbumTrack(){
		if(library.getAlbumCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displayAlbumLibrary();
			System.out.print("Album Number to update Track: ");
			int index = sc.nextInt() - 1;
			System.out.println("");

			if(index >= 0 && index < library.getAlbumCount()){
				Album album = library.getAlbumEntry(index);
				album.updateProgress();
				System.out.println(album.getProgress());
			}
			else{
				System.out.println("Invalid Album Number");
			}
		}

		
	}
	
	/**
	 * Moves a series entry's episode/season in the user's library
	 */
	public void moveSeriesEpisode(){
		if(library.getSeriesCount() > 0){
			Scanner sc = new Scanner(System.in);
			library.displaySeriesLibrary();
			System.out.print("Series Number to update Episode: ");
			int index = sc.nextInt() - 1;
			System.out.println("");

			if(index >= 0 && index < library.getSeriesCount()){
				Series series = library.getSeriesEntry(index);
				series.updateProgress();
				System.out.println(series.getProgress());
			}
			else{
				System.out.println("Invalid Series Number");
			}
		}

		
	}

	//GETTERS 
	/**
	  * Returns the user's username
	  * @return the user's username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	  * Returns the user's name
	  * @return the user's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	  * Returns the user's library
	  * @return the user's library
	 */
	public Library getLibrary()
	{
		return library;
	}
}
