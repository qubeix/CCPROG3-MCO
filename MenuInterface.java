import java.util.*;

public class MenuInterface
{
  //MENU
  public static void main(String[] args)
  {
    //Variables
    Scanner sc = new Scanner(System.in);
    int startOption = 0;
    int menuOption;
    int addOption;
    int removeOption;
    int viewOption;
    int i;    //loop variable

    User[] users = new User[100];   //stores the created accounts
    int userCount = 0;
    int userIndex;
    String name;
    String username;
    String password;
    boolean isValidUsername = true;

    String whiteSpace;


    //Main Code
    while(startOption != 3)
    {
      System.out.println("MEDIA VAULT\n");
      System.out.println("[1] Log In");
      System.out.println("[2] Create Account");
      System.out.println("[3] Close");

      System.out.print("\n> ");

      do{
        startOption = sc.nextInt();
      } while(startOption < 1 || startOption > 3);
      
      System.out.println("");
      whiteSpace = sc.nextLine();

      switch(startOption)
      {
        //Log In
        case 1:
          System.out.println("LOG IN");
          System.out.print("Username: ");
          username = sc.nextLine();
          System.out.print("Password: ");
          password = sc.nextLine();

          System.out.println("");

          userIndex = -1;
          for(i=0; i<userCount && userIndex==-1; i++)
            if(users[i].getUsername().equals(username))
              userIndex = i;

          if(userIndex!=-1)   //If an account with a matching username is found
          {
            if(users[userIndex].login(username, password))  //Validate log-in
            {
              menuOption = 0;
              while(menuOption != 4)
              {
                System.out.println("What would you like to do?");
                System.out.println("[1] Add Entries");
                System.out.println("[2] Remove Entries");
                System.out.println("[3] View Entries");
                System.out.println("[4] Log out");

                System.out.print("\n> ");

                do{
                  menuOption = sc.nextInt();
                } while(menuOption < 1 || menuOption > 4);

                System.out.println("");

                //Switch Cases for Main Menu
                switch(menuOption)
                {
                  case 1:
                    System.out.println("MENU: Add Entries");
                    System.out.println("[1] Add Book");
                    System.out.println("[2] Add Album");
                    System.out.println("[3] Add Series");

                    System.out.print("\n> ");

                    do{
                      addOption = sc.nextInt();
                    } while(addOption < 1 || addOption > 3);

                    System.out.println("");
                    whiteSpace = sc.nextLine();

                    //Switch Cases for Adding Entries
                    switch(addOption)
                    {
                      case 1:
                        users[userIndex].addBookEntry();
                        //whiteSpace = sc.nextLine();
                        break;
                      
                      case 2:
                        users[userIndex].addAlbumEntry();
                        //whiteSpace = sc.nextLine();
                        break;
                      
                      case 3:
                        users[userIndex].addSeriesEntry();
                        //whiteSpace = sc.nextLine();
                    }

                    break;
                  
                  case 2:
                    System.out.println("MENU: Remove Entries");
                    System.out.println("[1] Remove Book");
                    System.out.println("[2] Remove Album");
                    System.out.println("[3] Remove Series");

                    System.out.print("\n> ");

                    do{
                      removeOption = sc.nextInt();
                    } while(removeOption < 1 || removeOption > 3);

                    System.out.println("");
                    whiteSpace = sc.nextLine();

                    //Switch Cases for Removing Entries
                    switch(removeOption)
                    {
                      case 1:
                        users[userIndex].removeBookEntry();
                        break;
                      
                      case 2:
                        users[userIndex].removeAlbumEntry();
                        break;
                      
                      case 3:
                        users[userIndex].removeSeriesEntry();
                    }

                    break;

                  case 3:
                    System.out.println("MENU: View Entries");
                    System.out.println("[1] View by Status");
                    System.out.println("[2] View by Media Type");
                    System.out.println("[3] View by Status and Media Type");

                    System.out.print("\n> ");

                    do{
                      viewOption = sc.nextInt();
                    } while(viewOption < 1 || viewOption > 3);

                    System.out.println("");
                    whiteSpace = sc.nextLine();

                    //Switch Cases for Viewing Entries
                    switch(viewOption)
                    {
                      case 1:
                        users[userIndex].viewByStatus();
                        break;
                      
                      case 2:
                        users[userIndex].viewByType();
                        break;
                      
                      case 3:
                        users[userIndex].viewByStatusType();
                    }

                    break;
                  
                  case 4:
                    startOption = 0;  //Revert back to default
                }
              }

            }
          } else    //If no account is found
          {
            System.out.println("This account does not exist.\n");
          }

          break;

        //Create Account  
        case 2:
          System.out.println("CREATE ACCOUNT");
          System.out.print("Name: ");
          name = sc.nextLine();

          do{
            isValidUsername = true;              //reverts back to default

            System.out.print("Username: ");
            username = sc.nextLine();

            for(i=0; i<userCount && isValidUsername; i++)    //Ensure created username is not already in use
            {
              if(users[i].getUsername().equals(username))
              {
                isValidUsername = false;
                System.out.println("Invalid: This username is already taken.");
              }
            }
          } while(!isValidUsername);

          System.out.print("Password: ");
          password = sc.nextLine();

          users[userCount] = new User(username, password, name);  //Create account
          userCount++;
          System.out.println("\nAccount successfully created!\n");

          isValidUsername = true;   //Revert back to default value
      }
    }

    sc.close();
  }
}
