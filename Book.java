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

        //Initialization
        this.status = "Planned";
        this.rating = 0;
        this.review = "";
    }

    //Methods
    public void addStatus(String status)
    {
        if(status.equals("Planned") || status.equals("In Progress") || status.equals("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    public void updateStatus(String newStatus)
    {
        if(newStatus.equals("Planned") || newStatus.equals("In Progress") || newStatus.equals("Completed"))  //If the new status is valid
            this.status = newStatus;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    public void addRating(int rating)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;  //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed.");  //Display a message
    }

    public void addReview(String review)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed.");  //Display a message
    }

    //Getters
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
