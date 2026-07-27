package com.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String USER_FILE = "./users.txt";

    public static String getUserFile(){
        return USER_FILE;
    }


    public static void addUsers(String filePath, User user) throws IOException {
        boolean fileExist = new File(filePath).length() > 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
           
            if(fileExist){
                writer.newLine();
            }
           
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
        //StringBuilder content = new StringBuilder();
        User readUser = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean found = false;

            String name;
            String username;
            String password;

            int currentLine = 1;

            line = reader.readLine();
            while(line !=null && !found){
                if(currentLine == userLine){
                    String[] parts = line.split("\\|");
                    if(parts.length == 3){
                        name = parts[0];
                        username = parts[1];
                        password = parts[2];
                        readUser = new User(username, password, name);
                    }
                    found = true;
                }
                else {
                    currentLine++;
                    line = reader.readLine();
                }
            }

            /*while ((line = reader.readLine()) != null) {
                if (currentLine == userLine) {
                    Scanner sc = new Scanner(line);
                    // read name
                    int separatorIndex1 = line.indexOf('|');
                    name = line.substring(0, separatorIndex1);

                    // read username
                    int separatorIndex2 = separatorIndex1 + 1 + line.substring(separatorIndex1 + 1).indexOf('|');
                    username = line.substring(separatorIndex1 + 1, separatorIndex2);
                    password = line.substring(separatorIndex2 + 1);

                    // read password
                    password = line.substring(separatorIndex2 + 1, line.length());

                    readUser = new User(username, password, name);

                    sc.close();
                }
            }
        }*/
        return readUser;
        }
    }
}