/**
  * The class <code>Book</code> contains the attributes, constructor, methods, and getters necessary for the creation, modification, and manipulation of a book entry with:<br>
  * the common methods of: assigning a status (and modifying it), assigning a rating, and assigning a review; and<br>
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
    /**
      * Accepts a title, author, genre, and count of chapters as parameters, and initializes status to "Planned," rating to 0, review to empty, current chapter to 0 
      * @param title the title of the book
      * @param author the artist of the book
      * @param genre the genre of the book
      * @param chapterCount the count of chapters in the book
     */
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
      * Assigns a status ("Planned", "In Progress", or "Completed") to an entry
      * @param status the status to be assigned
     */
    public void addStatus(String status)
    {
        if(status.equalsIgnoreCase("Planned") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Completed"))  //If the status is valid
            this.status = status;  //Assign the new status
        else                                       //If the new status is not valid
            System.out.println("Invalid status");  //Display a message
    }

    /**
      * Modifies an entry's status ("Planned", "In Progress", or "Completed")
      * @param newStatus the status to be newly assigned
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
      * Assigns a rating to an entry
      * @param rating the number rating to be assigned
      * @pre. the entry must have a status of "Completed" to assign a rating
     */
    public void addRating(int rating)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.rating = rating;       //Assign the rating
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * Assigns a review to an entry
      * @param review the review to be assigned
      * @pre. the entry must have a status of "Completed" to assign a review
     */
    public void addReview(String review)
    {
        if(status.equalsIgnoreCase("Completed"))  //If the entry's status is "Completed"
            this.review = review;       //Assign the review
        else                                                        //If the entry's status is not "Completed"
            System.out.println("This entry is yet to be completed\n");  //Display a message
    }

    /**
      * Moves the current chapter forward by one
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
    /**
   	  * Returns the status of the book
   	  * @return the status
   	 */
    public String getStatus()
    {
        return status;
    }

    /**
   	  * Returns the rating of the book
   	  * @return the rating
   	 */
    public int getRating()
    {
        return rating;
    }

    /**
   	  * Returns the review of the book
   	  * @return the review
   	 */
    public String getReview()
    {
        return review;
    }

    /**
   	  * Returns the title of the book
   	  * @return the title
   	 */
    public String getTitle()
    {
        return TITLE;
    }

    /**
   	  * Returns the author of the book
   	  * @return the author
   	 */
    public String getAuthor()
    {
        return AUTHOR;
    }

    /**
   	  * Returns the number of chapters in the book
   	  * @return the number of chapters
   	 */
    public int getChapterCount()
    {
        return CHAPTERCOUNT;
    }

    /**
   	  * Returns the genre of the book
   	  * @return the genre
   	 */
    public String getGenre()
    {
        return GENRE;
    }

    /**
   	  * Returns the progress of the book, in the format of: "{@literal <current chapter> / <total chapters> Chapters}"
   	  * @return the progress
   	 */
    public String getProgress()
    {
        return currentChapter + "/" + CHAPTERCOUNT + " Chapters\n";   //return the amount of chapters read
    }
}
