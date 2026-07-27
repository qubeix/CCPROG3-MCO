package com.example.model;

import java.util.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;
import com.example.model.*;

public class FileManager {

    private static final String USER_FILE = "./users.txt";

    // public static void saveUsers(User[] users, int userCount) throws IOException
    // {
    // int i;
    // for (i = 0; i < userCount; i++)
    // {
    // try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(p,
    // CREATE, APPEND)))
    // }
    // }

    public static void addUsers(String filePath, User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(user.getName() + "|" + user.getUsername() + "|" + user.getPassword());
        }
    }

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

    public static User readUsers(String filePath, int userLine) throws IOException {
        StringBuilder content = new StringBuilder();
        User readUser = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            String name;
            String username;
            String password;

            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine == userLine) {
                    Scanner sc = new Scanner(line);
                    // read name
                    int separatorIndex1 = line.indexOf('|');
                    name = line.substring(0, separatorIndex1);

                    // read username
                    int separatorIndex2 = line.substring(separatorIndex1 + 1, line.length()).indexOf('|');
                    username = line.substring(separatorIndex1 + 1, separatorIndex2);

                    // read password
                    password = line.substring(separatorIndex2 + 1, line.length());

                    readUser = new User(username, password, name);

                    sc.close();
                }
            }
        }
        return readUser;
    }

    /**
     * Saves all registered users'credentials to the text file
     * 
     * @param users     an array of users to save
     * @param userCount is the number of active users in the array
     * @throws IOException if the file can't be written
     */
    /*
     * public static void saveUsers(User[] users, int userCount) throws IOException
     * {
     * BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
     * for (int i = 0; i < userCount; i++) {
     * writer.write(users[i].getUsername() + "," + users[i].getPassword() + "," +
     * users[i].getName());
     * writer.newLine();
     * }
     * writer.close();
     * }
     */

    /**
     * Loads the previously saved users data from the text file into the given array
     * 
     * @param users the array to populate
     * @return the number of users successfully loaded
     * @throws IOException if the file can't be read
     */
    /*
     * public static int loadUsers(User[] users) throws IOException {
     * int loadedCount = 0;
     * File file = new File("users.txt");
     * 
     * if (file.exists()) {
     * BufferedReader reader = new BufferedReader(new FileReader(file));
     * String line = reader.readLine();
     * 
     * while (line != null) {
     * String[] parts = line.split(",");
     * if (parts.length == 3) {
     * users[loadedCount] = new User(parts[0], parts[1], parts[2]);
     * loadedCount++;
     * }
     * line = reader.readLine();
     * }
     * reader.close();
     * }
     * return loadedCount;
     * }
     */

    /**
     * Saves the user's library (book, album, series) to a text file named after the
     * user's username
     * 
     * @param user the user whoes library should be saved
     * @throws IOException if the file can't be written
     */
    /*
     * @SuppressWarnings("resource")
     * public static void saveLib(User user) throws IOException {
     * Library library = user.getLibrary();
     * BufferedWriter writer = new BufferedWriter(new FileWriter(user.getUsername()
     * + "_lib.txt"));
     * 
     * for (int i = 0; i < library.getBookCount(); i++) {
     * Book book = library.getBookEntry(i);
     * writer.write("BOOK| " + book.getTitle() + "|" + book.getAuthor() + "|" +
     * book.getGenre() + "|"
     * + book.getChapterCount() + "|" + book.getStatus() + "|" + book.getRating() +
     * "|"
     * + book.getReview());
     * writer.newLine();
     * }
     * 
     * for (int i = 0; i < library.getAlbumCount(); i++) {
     * Album album = library.getAlbumEntry(i);
     * writer.write("ALBUM| " + album.getTitle() + "|" + album.getArtist() + "|" +
     * album.getGenre() + "|"
     * + album.getTrackCount() + "|" + album.getStatus() + "|" + album.getRating() +
     * "|"
     * + album.getReview());
     * writer.newLine();
     * }
     * 
     * for (int i = 0; i < library.getSeriesCount(); i++) {
     * Series series = library.getSeriesEntry(i);
     * writer.write("SERIES| " + series.getTitle() + "|" + series.getStudio() + "|"
     * + series.getGenre() + "|"
     * + series.getSeasonCount() + "|" + series.getStatus() + "|" +
     * series.getRating() + "|"
     * + series.getReview());
     * writer.newLine();
     * }
     * }
     */

}