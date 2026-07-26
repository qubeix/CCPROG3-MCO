package com.example.model;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileManager {
    BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
    for(int i = 0; i < userCount; i++){
        writer.write(users[i].getUsername() + "," + users[i].getPassword() + "," + users[i].getName());
        writer.newLine();
    }
    writer.close();
}

public static int loadUsers(User[] users) throw IOException{
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

public static void saveLib(User user) throws IOException{
    Library library = user.getLibrary();
    BufferedWriter writer = new BufferedWritter(New FileWriter(user.getUsername() + "_lib.txt"));

    for(int i = 0; i < library.getBookCount(); i++){
        Book book = library.getBookEntry(i);
    }
}