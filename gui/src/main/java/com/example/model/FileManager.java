package com.example.model;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileManager {

/**
 * Saves all registered users'credentials to the text file
 * @param users an array of users to save
 * @param userCount is the number of active users in the array
 * @throws IOException if the file can't be written 
 */
public static void saveUsers(User[] users, int userCount) throws IOException{
    BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
    for(int i = 0; i < userCount; i++){
        writer.write(users[i].getUsername() + "," + users[i].getPassword() + "," + users[i].getName());
        writer.newLine();
    }
    writer.close();
}

/**
 * Loads the previously saved users data from the text file into the given array
 * @param users the array to populate
 * @return the number of users successfully loaded 
 * @throws IOException if the file can't be read
 */

public static int loadUsers(User[] users) throws IOException {
    int loadedCount = 0;
    File file = new File("users.txt");

    if(file.exists()){
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null){
            String[] parts = line.split(",");
            if(parts.length == 3){
                users[loadedCount] = new User(parts[0], parts[1], parts[2]);
                loadedCount++;
            }
            line = reader.readLine();
        }
        reader.close();
    }
    return loadedCount;
}

/**
 * Saves the user's library (book, album, series) to a text file named after the user's username
 * @param user the user whoes library should be saved
 * @throws IOException if the file can't be written 
 */

public static void saveLib(User user) throws IOException{
    Library library = user.getLibrary();
    BufferedWriter writer = new BufferedWriter(new FileWriter(user.getUsername() + "_lib.txt"));

    for(int i = 0; i < library.getBookCount(); i++){
        Book book = library.getBookEntry(i);
        writer.write("BOOK| " + book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|"
                    + book.getChapterCount() + "|" + book.getStatus() + "|" + book.getRating() + "|" + book.getReview());
        writer.newLine();
    }

    for(int i = 0; i < library.getAlbumCount(); i++){
        Album album = library.getAlbumEntry(i);
        writer.write("ALBUM| " + album.getTitle() + "|" + album.getArtist() + "|" + album.getGenre() + "|"
                    + album.getTrackCount() + "|" + album.getStatus() + "|" + album.getRating() + "|" + album.getReview());
        writer.newLine();
    }

    for(int i = 0; i < library.getSeriesCount(); i++){
        Series series = library.getSeriesEntry(i);
        writer.write("SERIES| " + series.getTitle() + "|" + series.getStudio() + "|" + series.getGenre() + "|"
                    + series.getSeasonCount() + "|" + series.getStatus() + "|" + series.getRating() + "|" + series.getReview());
        writer.newLine();
    }
}