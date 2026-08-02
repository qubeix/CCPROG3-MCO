package com.example.model;

import java.util.Scanner;

/**
 * The class <code>User</code> contains the attributes, constructor, methods,
 * and getters necessary for the creation, validation, and actions of a user,
 * such as<br>
 * the methods of: logging in, logging out, adding entries, removing entries,
 * viewing libraries, and getting their library summary
 */

public class User {
	// ATTRIBUTES
	private String username;
	private String password;
	private String name;
	private Library library;

	// CONSTRUCTORS
	/**
	 * Accepts a username, password, and name as parameters, and initializes them as
	 * the user's username, password, and name, respectively. A Library is also
	 * instantiated.
	 * 
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param name     the name of the user
	 */
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.library = new Library();
	}

	// METHODS
	/**
	 * Logs a user into their account
	 * @pre. user exists
	 * 
	 * @param inputUsername the username provided by the user in logging in
	 * @param inputPassword the password provided by the user in logging in
	 * @return true if the inputted password matches the password of the inputted
	 *         username, false otherwise
	 */
	public boolean login(String inputUsername, String inputPassword) {
		if (inputUsername.equals(username) && inputPassword.equals(password)) {
			System.out.println("Login Successful! Welcome, " + name + "!\n");
			return true;
		} else {
			System.out.println("Invalid username or password");
			return false;
		}
	}

	/**
	 * Logs a user out of their account
	 * @pre. user has been logged in
	 */
	public void logout() {
		System.out.println("Thank you! " + name + " has logged out\n");
	}

	// GETTERS
	/**
	 * Returns the user's username
	 * 
	 * @return the user's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the user's password
	 * 
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the user's name
	 * 
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the user's library
	 * 
	 * @return the user's library
	 */
	public Library getLibrary() {
		return library;
	}

	// SETTERS
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
}