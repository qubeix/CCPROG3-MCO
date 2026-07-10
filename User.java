import java.util.*;

/**
 * The User class contains the attributes, constructor, methods, and getters necessary for the creation, validation, and actions of a user, such as
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
	public User (String username, String password, String name)
	{
		this.username = username;
		this.password = password;
		this.name = name;
		this.library = new Library();
	}

	//METHODS
	/**
	 * login() logs a user into their account
	 * @pre user exists
	 * @param inputUsername - the username provided by the user in logging in
	 * @param inputPassword - the password provided by the user in logging in
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
	 * logout() logs a user out of their account
	 * @pre user has been logged in
	 */ 
	public void logout()
	{
		System.out.println("Thank you! " +name+ " has logged out\n");
	}

	/**
	 * addBookEntry() adds a book entry into the user's library
	 */
	public void addBookEntry(){
		library.addBook();
		System.out.println("Book added to library\n");
	}

	/**
	 * addAlbumEntry() adds an album entry into the user's library
	 */
	public void addAlbumEntry(){
		library.addAlbum();
		System.out.println("Album added to library\n");
	}

	/**
	 * addSeriesEntry() adds a series entry into the user's library
	 */
	public void addSeriesEntry(){
		library.addSeries();
		System.out.println("Series added to library\n");
	}
	
	/**
	 * removeBookEntry() removes a book entry from the user's library
	 * @pre - book entry exists in library
	 */
	public void removeBookEntry(){
		library.removeBook();
	}
	
	/**
	 * removeAlbumEntry() removes an album entry from the user's library
	 * @pre - album entry exists in library
	 */
	public void removeAlbumEntry(){
		library.removeAlbum();
	}
	
	/**
	 * removeSeriesEntry() removes a series entry from the user's library
	 * @pre - series entry exists in library
	 */
	public void removeSeriesEntry(){
		library.removeSeries();
	}

	/**
	 * viewLibrary() displays the user's libraries (book, album, and series)
	 */ 
	public void viewLibrary()
	{
		library.displayLibrary();
	}
	
	/**
	 * viewByStatus() displays the user's libraries of a given status (Planned, In Progress, or Completed)
	 */ 
	public void viewByStatus(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter status (Planned, In Progress, Completed): ");
		String status = sc.nextLine();
		System.out.println("");
		library.filterByStatus(status);
	}
	
	/**
	 * viewByType() displays the user's library of a given media type (Book, Album, or Series)
	 */ 
	public void viewByType(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter type (Book, Album, Series): ");
		String type = sc.nextLine();
		System.out.println("");
		library.filterByType(type);
	}

	/**
	 * viewByStatusType() displays the user's library of a given status (Planned, In Progress, or Completed) and given media type (Book, Album, or Series)
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
	 * getSummary() displays the user's library summary
	 */ 
	public void getSummary()
	{
		library.summary();
	}

	/**
	 * rateBookEntry() rates a book entry in the user's library
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
	 * rateAlbumEntry() rates an album entry in the user's library
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
	 * rateSeriesEntry() rates a series entry in the user's library
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
	 * reviewBookEntry() reviews a book entry in the user's library
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
	 * reviewAlbumEntry() reviews an album entry in the user's library
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
	 * reviewSeriesEntry() reviews a series entry in the user's library
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
	 * updateBookStatus() updates a book entry's status in the user's library
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
	 * updateAlbumStatus() updates an album entry's status in the user's library
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
	 * updateSeriesStatus() updates a series entry's status in the user's library
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
	 * moveBookChapter() moves a book entry's chapter in the user's library
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
				book.nextChapter();
				System.out.println(book.getProgress());
			}
			else{
				System.out.println("Invalid Book Number");
			}
		}
	}

	/**
	 * moveAlbumTrack() moves an album entry's track in the user's library
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
				album.nextTrack();
				System.out.println(album.getProgress());
			}
			else{
				System.out.println("Invalid Album Number");
			}
		}

		
	}
	
	/**
	 * moveSeriesEpisode() moves a series entry's episode/season in the user's library
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
				series.nextEpisode();
				System.out.println(series.getProgress());
			}
			else{
				System.out.println("Invalid Series Number");
			}
		}

		
	}

	//GETTERS 
	public String getUsername()
	{
		return username;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Library getLibrary()
	{
		return library;
	}
}
