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

    private final int CHAPTERCOUNT;     //the number of chapters
    private int currentChapter;         //the user's current chapter

    //Constructor
    public Book(String title, String author, String genre, int chapterCount)
    {
        TITLE = title;
        AUTHOR = author;
        GENRE = genre;

        CHAPTERCOUNT = chapterCount;
        
        //Initialization
        status = "Planned";
        rating = 0;
        review = "";
        currentChapter = 0;
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
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    public void addReview(String review)
    {
        if(status.equals("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to completed");  //Display a message
    }

    public void nextChapter()
    {
        if(currentChapter < CHAPTERCOUNT)   //If the current chapter has not reached the last chapter
        {
            currentChapter++;                   //Move to the next chapter

            if(status.equals("Planned"))           //If the status if "Planned"
                updateStatus("In Progress");       //Update the status to "In Progress"
            
            if(currentChapter == CHAPTERCOUNT)     //If the current chapter is the last chapter
                updateStatus("Completed");         //Update the status to "Completed"
        }
        else                                                                //If the book is completed
            System.out.println("You have already completed this book");    //Display a message
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

    public String getTitle()
    {
        return TITLE;
    }

    public String getAuthor()
    {
        return AUTHOR;
    }

    public String getGenre()
    {
        return GENRE;
    }

    public String getProgress()
    {
        return currentChapter + "/" + CHAPTERCOUNT + " Chapters";   //return the amount of chapters read
    }
}
