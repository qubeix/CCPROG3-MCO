import java.util.*;

public class Book
{
    //Variables
    private String status;
    private int rating;
    private String review;

    private final String TITLE;
    private final String AUTHOR;
    private final String GENRE;

    //Constructor
    public Book(String title, String author, String genre)
    {
        TITLE = title;
        AUTHOR = author;
        GENRE = genre;
    }

    //Methods
    public void addStatus()
    {

    }

    public void updateStatus()
    {

    }

    public void addRating()
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
        {   //Get rating and assign
            Scanner sc = new Scanner(System.in);
            System.out.print("Rating: ");
            rating = sc.nextInt();
        } else                          //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed.");  //Display a message
    }

    public void addReview()
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
        {   //Get review and assign
            Scanner sc = new Scanner(System.in);
            System.out.println("Review:");
            review = sc.nextLine();
        } else                          //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed.");  //Display a message
    }

    public String getStatus()
    {
        return status;
    }

    public int getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }
}
