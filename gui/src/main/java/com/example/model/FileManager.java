package com.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String USER_FILE = "./users.txt";
    //private static final String USER_FILE="./gui/users.txt";
    //private static final String FOLDER_PATH="./gui/";
    private static final String LIBRARY_FILE = "_library.txt";

    public static String getUserFile() {
        return USER_FILE;
    }

    public static void addUsers(String filePath, User user) throws IOException {
        //boolean fileExist = new File(filePath).length() > 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            // if (fileExist) {
            //     writer.newLine();
            // }

            writer.write(user.getName() + "|" + user.getUsername() + "|" + user.getPassword());
            writer.newLine();
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
        // StringBuilder content = new StringBuilder();
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

    public static void saveLibrary(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./" + user.getUsername() + LIBRARY_FILE))) {
            writer.write(user.getLibrary().getAllEntriesText());
        }

    }

    public static Library loadLibrary(User user) throws IOException {
        Library readlibrary = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("./" + user.getUsername() + LIBRARY_FILE))) {
            String line;

            line = reader.readLine(); // read "Book Library"

            while (line != "")
                line = reader.readLine(); // read Book Library entries

            line = reader.readLine(); // read line of total books
            int totalBooks = Integer.parseInt(line.substring(line.indexOf(':') + 2).trim()); // get total books
            line = reader.readLine(); // read line of total albums
            int totalAlbums = Integer.parseInt(line.substring(line.indexOf(':') + 2).trim()); // get total albums
            line = reader.readLine(); // read line of total series
            int totalSeries = Integer.parseInt(line.substring(line.indexOf(':') + 2).trim()); // get total series

            line = reader.readLine(); // read line of total entries

            line = reader.readLine(); // read empty line

            for (int i = 0; i < totalBooks; i++) {
                line = reader.readLine(); // read empty line
            }
        }
        

        return readlibrary;
    }
}