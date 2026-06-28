import java.util.*;

public class MenuInterface
{
  //HELPER METHODS
  /**
   * isValidOption() checks whether the user-inputted option is valid or not
   * @param first - the first option available from the numbered list of options
   * @param last - the last option available from the numbered list of options
   * @return true if the option is valid, otherwise false
   */
  public static boolean isValidOption(int option, int first, int last)
  {
    if(option >= first && option <= last)
      return true;
    else
      return false;
  }

  //MENU
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    int option = 0;

    while(option != 3)
    {
      System.out.println("MEDIA VAULT\n");
      System.out.println("[1] Log In");
      System.out.println("[2] Create Account");
      System.out.println("[3] Close");

      System.out.print("> ");

      do{
        option = sc.nextInt();
      } while(!isValidOption(option, 1, 3));

      switch(option)
      {
        case 1:
          //Codes for Log In
          break;
          
        case 2:
          //Code for Create Account
      }
    }

    sc.close();
  }
}
