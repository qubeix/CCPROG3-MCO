package com.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for managing user and library data files.
 * Provides methods to read, write, and update user and library information.
 */
public class FileManager {

    /** Path to the user data file. */
    private static final String USER_FILE = "./users.txt";

    /**
     * returs the path or name of the user file
     * 
     * @return the usuer file path or name
     */
    public static String getUserFile() {
        return USER_FILE;
    }

    /**
     * Adds a new user to the user file
     * 
     * appends the provided user's information (name, username, password). Each user
     * entry is written on a new line sepereated by the "|" character. Uses a
     * BufferedWriter with append mode to ensure that all existing data remain
     * preserved
     * 
     * @param filePath the path of the file where user data is sstored
     * @param user     the user object containing the details to add
     * @throws IOException if an error occurs while writing to the file
     */
    public static void addUsers(String filePath, User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getName() + "|" + user.getUsername() + "|" + user.getPassword());
            writer.newLine();
        }
    }

    /**
     * loads users from a file into the provided array
     * 
     * @param filePath the path of the file containing user date
     * @param users    the array where loaded User objects are stored
     * @return the number of users successfully loaded
     * @throws IOException if an error occurs while reading the fiel
     */
    public static int loadUsers(String filePath, User[] users) throws IOException {
        int loadedCount = 0;
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();

                while (line != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 3) {
                        users[loadedCount] = new User(parts[1], parts[2], parts[0]);
                        loadedCount++;
                    }
                    line = reader.readLine();
                }
            }
        }
        return loadedCount;
    }

    /**
     * Reads a single user from the tile at teh given line number
     * 
     * @param filePath the path of the user file
     * @param userLine the line number of the user to read
     * @return the User object at the specified line, or null if not found
     * @throws IOException if an error occurs while reading the file
     */
    public static User readUsers(String filePath, int userLine) throws IOException {
        User readUser = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean found = false;

            String name;
            String username;
            String password;

            int currentLine = 1;

            line = reader.readLine();
            while (line != null && !found) {
                if (currentLine == userLine) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 3) {
                        name = parts[0];
                        username = parts[1];
                        password = parts[2];
                        readUser = new User(username, password, name);
                    }
                    found = true;
                } else {
                    currentLine++;
                    line = reader.readLine();
                }
            }

            return readUser;
        }
    }

    /**
     * rewrites the user file with all current users
     * 
     * @param filePath  the path of the user file
     * @param users     the array of User object to write
     * @param userCount the number of users to include
     * @throws IOException if an error occurs while writing to the file
     */
    public static void rewriteAllUsers(String filePath, User[] users, int userCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (int i = 0; i < userCount; i++) {
                writer.write(users[i].getName() + "|" + users[i].getUsername() + "|" + users[i].getPassword());
                writer.newLine();
            }
        }
    }

    // /**
    // * savevs the user's library to a file
    // *
    // * @param user the User whose library will be saved
    // * @throws IOException if an error occurs while writing to the file
    // */
    // public static void saveLibrary(User user) throws IOException {
    // try (BufferedWriter writer = new BufferedWriter(new FileWriter("./" +
    // user.getUsername() + LIBRARY_FILE))) {
    // writer.write(user.getLibrary().getAllEntriesText());
    // }

    // }

    // /**
    // * loads a user's library from file
    // *
    // * @param user the User whose library will be loaded
    // * @return the Library object read from the file, or null of not built
    // * @throws IOException if an error occurs while reading the file
    // */
    // public static Library loadLibrary(User user) throws IOException {
    // Library readlibrary = null;

    // try (BufferedReader reader = new BufferedReader(new FileReader("./" +
    // user.getUsername() + LIBRARY_FILE))) {
    // String line;

    // line = reader.readLine(); // read "Book Library"

    // while (line != "")
    // line = reader.readLine(); // read Book Library entries

    // line = reader.readLine(); // read line of total books
    // int totalBooks = Integer.parseInt(line.substring(line.indexOf(':') +
    // 2).trim()); // get total books
    // line = reader.readLine(); // read line of total albums
    // int totalAlbums = Integer.parseInt(line.substring(line.indexOf(':') +
    // 2).trim()); // get total albums
    // line = reader.readLine(); // read line of total series
    // int totalSeries = Integer.parseInt(line.substring(line.indexOf(':') +
    // 2).trim()); // get total series

    // line = reader.readLine(); // read line of total entries

    // line = reader.readLine(); // read empty line

    // for (int i = 0; i < totalBooks; i++) {
    // line = reader.readLine(); // read empty line
    // }
    // }

    // return readlibrary;
    // }
}