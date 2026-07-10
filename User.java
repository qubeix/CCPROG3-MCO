import java.util.*;

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
	//login method for user 
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
	
	//logout display/method 
	public void logout()
	{
		System.out.println("Thank you! " +name+ " has logged out\n");
	}

	//add the different media types
	public void addBookEntry(){
		library.addBook();
		System.out.println("Book added to library\n");
	}

	public void addAlbumEntry(){
		library.addAlbum();
		System.out.println("Album added to library\n");
	}

	public void addSeriesEntry(){
		library.addSeries();
		System.out.println("Series added to library\n");
	}
	
	//remove the differnt media types
	public void removeBookEntry(){
		library.removeBook();
	}
	
	public void removeAlbumEntry(){
		library.removeAlbum();
	}
	
	public void removeSeriesEntry(){
		library.removeSeries();
	}

	//view media types entires 
	public void viewLibrary()
	{
		library.displayLibrary();
	}
	
	public void viewByStatus(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter status (Planned, In Progress, Completed): ");
		String status = sc.nextLine();
		System.out.println("");
		library.filterByStatus(status);
	}
	
	public void viewByType(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter type (Book, Album, Series): ");
		String type = sc.nextLine();
		System.out.println("");
		library.filterByType(type);
	}

	public void getSummary()
	{
		library.summary();
	}

	//rating methods
	public void viewByStatusType(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter status (Planned, In Progress, Completed): ");
		String status = sc.nextLine();
		System.out.print("Enter type (Book, Album, Series): ");
		String type = sc.nextLine();
		System.out.println("");
		library.filterByStatusType(status, type);
	}

	public void rateBookEntry(){
		if(library.getBookCount() == 0){
			library.displayBookLibrary();
			return;
		}
		
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

	public void rateAlbumEntry(){
		if(library.getAlbumCount() == 0){
			library.displayAlbumLibrary();
			return;
		}

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

	public void rateSeriesEntry(){
		if(library.getSeriesCount() == 0){
			library.displaySeriesLibrary();
			return;
		}
		
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

	//review methods
	public void reviewBookEntry(){
		if(library.getBookCount() == 0){
			library.displayBookLibrary();
			return;
		}

		Scanner sc = new Scanner(System.in);
		library.displayBookLibrary();
		System.out.print("Book Number to Review: ");
		int index = sc.nextInt() - 1;
		System.out.print("Type your review: ");
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

	public void reviewAlbumEntry(){
		if(library.getAlbumCount() == 0){
			library.displayAlbumLibrary();
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		library.displayAlbumLibrary();
		System.out.print("Album to Review: ");
		int index = sc.nextInt() - 1;
		System.out.print("Type your review: ");
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

	public void reviewSeriesEntry(){
		if(library.getSeriesCount() == 0){
			library.displaySeriesLibrary();
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		library.displaySeriesLibrary();
		System.out.print("Series to Review: ");
		int index = sc.nextInt() - 1;
		System.out.print("Type your review: ");
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

	//update status methods
	public void updateBookStatus(){
		if(library.getBookCount() == 0){
			library.displayBookLibrary();
			return;
		}

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

	public void updateAlbumStatus(){
		if(library.getAlbumCount() == 0){
			library.displayAlbumLibrary();
			return;
		}

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
	
	public void updateSeriesStatus(){
		if(library.getSeriesCount() == 0){
			library.displaySeriesLibrary();
			return;
		}

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

	//update progress 
	public void moveBookChapter(){
		if(library.getBookCount() == 0){
			library.displayBookLibrary();
			return;
		}

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

	public void moveAlbumTrack(){
		if(library.getAlbumCount() == 0){
			library.displayAlbumLibrary();
			return;
		}

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
	
	public void moveSeriesEpisode(){
		if(library.getSeriesCount() == 0){
			library.displaySeriesLibrary();
			return;
		}

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

	//getters 
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
