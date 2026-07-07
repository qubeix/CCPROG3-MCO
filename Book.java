import java.util.*;

/**
  * The Book class contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of a book entry with:
  * the common methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and
  * the unique method of: updating the book entry's current chapter progress
 */
public class Book
{
    //ATTRIBUTES
    private String status;
    private int rating;
    private String review;

    private final String TITLE;
    private final String AUTHOR;
    private final String GENRE;

    private final int CHAPTERCOUNT;     //the number of chapters
    private int currentChapter;         //the user's current chapter

    //CONSTRUCTOR
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

    //METHODS
    /**
      * addStatus() assigns a status ("Planned", "In Progress", or "Completed") to an entry
      * @param status - the status to be assigned
     */
    public void addStatus(String status)
    {
        if(status.equalsIgnoreCase("Planned") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    /**
      * updateStatus() modifies an entry's status ("Planned", "In Progress", or "Completed")
      * @param newStatus - the status to be newly assigned
     */
    public void updateStatus(String newStatus)
    {
        if(newStatus.equalsIgnoreCase("Planned") || newStatus.equalsIgnoreCase("In Progress") || newStatus.equalsIgnoreCase("Completed"))  //If the new status is valid
            this.status = newStatus;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message

        if(status.equalsIgnoreCase("Completed"))
            currentChapter = CHAPTERCOUNT;
    }

    /**
      * addRating() assigns a rating to an entry
      * @param rating - the number rating to be assigned
      * @pre - the entry must have a status of "Completed" to assign a rating
     */
    public void addRating(int rating)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * addReview() assigns a review to an entry
      * @param review - the review to be assigned
      * @pre - the entry must have a status of "Completed" to assign a review
     */
    public void addReview(String review)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * nextChapter() moves the current chapter forward by one
     */
    public void nextChapter()
    {
        if(currentChapter < CHAPTERCOUNT)   //If the current chapter has not reached the last chapter
        {
            currentChapter++;                   //Move to the next chapter

            if(status.equalsIgnoreCase("Planned"))           //If the status if "Planned"
                updateStatus("In Progress");       //Update the status to "In Progress"
            
            if(currentChapter == CHAPTERCOUNT)     //If the current chapter is the last chapter
                updateStatus("Completed");         //Update the status to "Completed"
        }
        else                                                                //If the book is completed
            System.out.println("You have already completed this book");    //Display a message
    }

    //GETTERS
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

    public int getChapterCount()
    {
        return CHAPTERCOUNT;
    }

    public String getGenre()
    {
        return GENRE;
    }

    public String getProgress()
    {
        return currentChapter + "/" + CHAPTERCOUNT + " Chapters\n";   //return the amount of chapters read
    }
}
