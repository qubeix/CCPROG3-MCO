import java.util.*;

public class User
{
	//variables 
	private String username;
	private String password;
	private String name;
	private Library library; 
	
	//constructors 
	public User (String username, String password, String name)
	{
		this.username = username;
		this.password = password;
		this.name = name;
		this.library = new Library();
	}

	//login method for user 
	public boolean login (String inputUsername, String inputPassword) 
	{
		if (inputUsername.equals(username) && inputPassword.equals(password))
		{
			System.out.println("Login Successful! Welcome, " +name+ "!");
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
		System.out.println("Thank you " +name+ "has logged out");
	}

	//add the different media types
	public void addBookEntry(){
		library.addBook();
		System.out.println("Book added to library");
	}

	public void addAlbumEntry(){
		library.addAlbum();
		System.out.println("Album added to library");
	}

	public void addSeriesEntry(){
		library.addSeries();
		System.out.println("Series added to library");
	}

	//view media types entires 
	public void viewLibrary()
	{
		library.displayLibrary();
	}
	
	public void getSummary()
	{
		library.summary();
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
