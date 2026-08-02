// package com.example.model;
// import java.util.*;

// /**
// * The class <code>MenuInterface</code> provides a text-based menu-driven
// interface for a media vault,
// * containing a book library, album library, and series library
// * and allows the existence of mutiple users
// */

// public class MenuInterface
// {
// //MENU
// /**
// * Contains the implementation of the text-based menu-driven interface for the
// media vault
// * @param args default parameter
// */
// public static void main(String[] args)
// {
// //Variables
// Scanner sc = new Scanner(System.in);
// int startOption = 0;
// int menuOption;
// int addOption;
// int removeOption;
// int rateOption;
// int reviewOption;
// int updateOption;
// int updateOption2;
// int viewOption;
// int i; //loop variable

// User[] users = new User[100]; //stores the created accounts
// int userCount = 0;
// int userIndex;
// String name;
// String username;
// String password;
// boolean isValidUsername = true;

// //Main Code
// while(startOption != 3)
// {
// System.out.println("MEDIA VAULT\n");
// System.out.println("[1] Log In");
// System.out.println("[2] Create Account");
// System.out.println("[3] Close");

// System.out.print("\n> ");

// do{
// startOption = sc.nextInt();
// } while(startOption < 1 || startOption > 3);

// System.out.println("");
// sc.nextLine();

// switch(startOption)
// {
// //Log In
// case 1:
// System.out.println("LOG IN");
// System.out.print("Username: ");
// username = sc.nextLine();
// System.out.print("Password: ");
// password = sc.nextLine();

// System.out.println("");

// userIndex = -1;
// for(i=0; i<userCount && userIndex==-1; i++)
// if(users[i].getUsername().equals(username))
// userIndex = i;

// if(userIndex!=-1) //If an account with a matching username is found
// {
// if(users[userIndex].login(username, password)) //Validate log-in
// {
// menuOption = 0;
// while(menuOption != 8)
// {
// System.out.println("What would you like to do?");
// System.out.println("[1] Add Entries");
// System.out.println("[2] Remove Entries");
// System.out.println("[3] Rate Entries");
// System.out.println("[4] Review Entries");
// System.out.println("[5] Update Entries");
// System.out.println("[6] View Entries");
// System.out.println("[7] View Summary");
// System.out.println("[8] Log out");

// System.out.print("\n> ");

// do{
// menuOption = sc.nextInt();
// } while(menuOption < 1 || menuOption > 8);

// System.out.println("");

// //Switch Cases for Main Menu
// switch(menuOption)
// {
// case 1:
// System.out.println("MENU: Add Entries");
// System.out.println("[1] Add Book");
// System.out.println("[2] Add Album");
// System.out.println("[3] Add Series");

// System.out.print("\n> ");

// do{
// addOption = sc.nextInt();
// } while(addOption < 1 || addOption > 3);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Adding Entries
// switch(addOption)
// {
// case 1:
// users[userIndex].addBookEntry();
// break;

// case 2:
// users[userIndex].addAlbumEntry();
// break;

// case 3:
// //users[userIndex].addSeriesEntry();
// }

// break;

// case 2:
// System.out.println("MENU: Remove Entries");
// System.out.println("[1] Remove Book");
// System.out.println("[2] Remove Album");
// System.out.println("[3] Remove Series");

// System.out.print("\n> ");

// do{
// removeOption = sc.nextInt();
// } while(removeOption < 1 || removeOption > 3);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Removing Entries
// switch(removeOption)
// {
// case 1:
// users[userIndex].removeBookEntry();
// break;

// case 2:
// users[userIndex].removeAlbumEntry();
// break;

// case 3:
// users[userIndex].removeSeriesEntry();
// }

// break;

// case 3:
// System.out.println("MENU: Rate Entries");
// System.out.println("[1] Rate Book");
// System.out.println("[2] Rate Album");
// System.out.println("[3] Rate Series");

// System.out.print("\n> ");

// do{
// rateOption = sc.nextInt();
// } while(rateOption < 1 || rateOption > 3);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Rating Entries
// switch(rateOption)
// {
// case 1:
// users[userIndex].rateBookEntry();
// break;

// case 2:
// users[userIndex].rateAlbumEntry();
// break;

// case 3:
// users[userIndex].rateSeriesEntry();
// }

// break;

// case 4:
// System.out.println("MENU: Review Entries");
// System.out.println("[1] Review Book");
// System.out.println("[2] Review Album");
// System.out.println("[3] Review Series");

// System.out.print("\n> ");

// do{
// reviewOption = sc.nextInt();
// } while(reviewOption < 1 || reviewOption > 3);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Reviewing Entries
// switch(reviewOption)
// {
// case 1:
// users[userIndex].reviewBookEntry();
// break;

// case 2:
// users[userIndex].reviewAlbumEntry();
// break;

// case 3:
// users[userIndex].reviewSeriesEntry();
// }

// break;

// case 5:
// System.out.println("MENU: Update Entries");
// System.out.println("[1] Update Book");
// System.out.println("[2] Update Album");
// System.out.println("[3] Update Series");

// System.out.print("\n> ");

// do{
// updateOption = sc.nextInt();
// } while(updateOption < 1 || updateOption > 3);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Updating Entries
// switch(updateOption)
// {
// case 1:
// System.out.println("MENU: Update Book");
// System.out.println("[1] Update Status");
// System.out.println("[2] Update Chapter");

// System.out.print("\n> ");

// do{
// updateOption2 = sc.nextInt();
// } while(updateOption2 < 1 || updateOption2 > 2);

// System.out.println("");
// sc.nextLine();

// //Switch Case for Updating Book
// switch(updateOption2)
// {
// case 1:
// users[userIndex].updateBookStatus();
// break;

// case 2:
// users[userIndex].moveBookChapter();
// }

// break;

// case 2:
// System.out.println("MENU: Update Album");
// System.out.println("[1] Update Status");
// System.out.println("[2] Update Track");

// System.out.print("\n> ");

// do{
// updateOption2 = sc.nextInt();
// } while(updateOption2 < 1 || updateOption2 > 2);

// System.out.println("");
// sc.nextLine();

// //Switch Case for Updating Album
// switch(updateOption2)
// {
// case 1:
// users[userIndex].updateAlbumStatus();
// break;

// case 2:
// users[userIndex].moveAlbumTrack();
// }

// break;

// case 3:
// System.out.println("MENU: Update Series");
// System.out.println("[1] Update Status");
// System.out.println("[2] Update Season/Episode");

// System.out.print("\n> ");

// do{
// updateOption2 = sc.nextInt();
// } while(updateOption2 < 1 || updateOption2 > 2);

// System.out.println("");
// sc.nextLine();

// //Switch Case for Updating Series
// switch(updateOption2)
// {
// case 1:
// users[userIndex].updateSeriesStatus();
// break;

// case 2:
// users[userIndex].moveSeriesEpisode();
// }
// }

// break;

// case 6:
// System.out.println("MENU: View Entries");
// System.out.println("[1] View by Status");
// System.out.println("[2] View by Media Type");
// System.out.println("[3] View by Status and Media Type");
// System.out.println("[4] View All");

// System.out.print("\n> ");

// do{
// viewOption = sc.nextInt();
// } while(viewOption < 1 || viewOption > 4);

// System.out.println("");
// sc.nextLine();

// //Switch Cases for Viewing Entries
// switch(viewOption)
// {
// case 1:
// users[userIndex].viewByStatus();
// break;

// case 2:
// users[userIndex].viewByType();
// break;

// case 3:
// users[userIndex].viewByStatusType();
// break;

// case 4:
// users[userIndex].viewLibrary();
// }

// break;

// case 7:
// users[userIndex].getSummary();
// break;

// case 8:
// users[userIndex].logout();
// startOption = 0; //Revert back to default
// break;
// }
// }

// }
// } else //If no account is found
// {
// System.out.println("This account does not exist.\n");
// }

// break;

// //Create Account
// case 2:
// System.out.println("CREATE ACCOUNT");
// System.out.print("Name: ");
// name = sc.nextLine();

// do{
// isValidUsername = true; //reverts back to default

// System.out.print("Username: ");
// username = sc.nextLine();

// for(i=0; i<userCount && isValidUsername; i++) //Ensure created username is
// not already in use
// {
// if(users[i].getUsername().equals(username))
// {
// isValidUsername = false;
// System.out.println("Invalid: This username is already taken.\n");
// }
// }
// } while(!isValidUsername);

// System.out.print("Password: ");
// password = sc.nextLine();

// users[userCount] = new User(username, password, name); //Create account
// userCount++;
// System.out.println("\nAccount successfully created!\n");

// isValidUsername = true; //Revert back to default value
// }
// }

// sc.close();
// }
// }