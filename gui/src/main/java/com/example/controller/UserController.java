package com.example.controller;

import java.io.IOException;

import com.example.model.FileManager;
import com.example.model.User;

/**
 * Controller class for managing user accounts in the application.
 * Provides methods to add, retrieve, update, and remove users,
 * as well as track the currently logged-in user.
 */
public class UserController {
    /** Path to the user data file. */
    private static final String USER_FILE = "./users.txt";

    /** Array storing all registered users. */
    private static User[] users = new User[100];

    /** Counter tracking the number of registered users. */
    private static int userCount = 0;

    /** Reference to the currently logged-in user. */
    private static User currentUser = null;

    /**
     * Retrieves the list of all registered users.
     * 
     * Returns the static users array, which contains teh currently stored User
     * object in the application
     * 
     * @return an array of User objects representing all users
     */
    public static User[] getUsers() {
        return users;
    }

    /**
     * Retrieves the total number of registered users.
     * 
     * Returns the static userCount value, which trakcs how many User objects are
     * currently stored in the application
     * 
     * @return the number of users currently registered
     */
    public static int getUserCount() {
        return userCount;
    }

    /**
     * Updates the total number of registered users.
     *
     * @param count the new total number of users
     */
    public static void setUserCount(int count) {
        userCount = count;
    }

    /**
     * Adds a new user to the application.
     * 
     * Stores the given User object in the static users array, increments the
     * userCount, and persists the new user by calling FileManager.addUsers with the
     * designated user file.
     * 
     * @param newUser the User object to be added
     * @throws IOException if an error occurs while writing the user data to the
     *                     file
     */
    public static void addUser(User newUser) throws IOException {
        users[userCount] = newUser;
        userCount++;
        FileManager.addUsers(USER_FILE, newUser);
    }

    /**
     * Retrieves the currently logged-in user.
     * 
     * Returns the static currentUser value, which represents the user who is
     * currently authenticated in the application
     * 
     * @return the current User object
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the curerntly logged-in user
     * 
     * updates the static currentUser value to the specified user, representing the
     * users logged-in
     * 
     * @param user the User object to set as the current user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Removes a user from the system by username.
     * 
     * Searches the users array for the specified username. If found,
     * shifts all subsequent users one position to the left to fill
     * the gap, sets the last user slot to null, and decrements the
     * userCount. If the username is not found, no changes are made.
     *
     * @param username the username of the user to remove
     */
    public static void removeUser(String username) {
        int indexToRemove = -1;
        for (int i = 0; i < userCount && indexToRemove == -1; i++) {
            if (users[i].getUsername().equals(username)) {
                indexToRemove = i;
            }
        }

        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < userCount - 1; i++) {
                users[i] = users[i + 1];
            }
            users[userCount - 1] = null;
            userCount--;
        }
    }
}