public abstract class MediaEntry
{
  //ATTRIBUTES
  protected String status;
  protected int rating;
  protected String review;
  protected final String TITLE;
  protected final String GENRE;

  //CONSTRUCTOR
  public MediaEntry(String title, String genre){
    TITLE = title;
    GENRE = genre;

    status = "Planned";
    rating = 0;
    review = "";
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
    else                                     //If the new status is not valid
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
    else                                     //If the new status is not valid
      System.out.println("Invalid status");  //Display a message
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
    else                                                          //If the entry's status is not "Completed"
      System.out.println("This entry is yet to be completed\n");  //Display a message
    }

  /**
    * Assigns a review to an entry
    * @param review the review to be assigned
    * @pre. the entry must have a status of "Completed" to assign a review
    */
  public void addReview(String review){
    if(status.equalsIgnoreCase("Completed")){  //If the entry's status is "Completed"
      this.review = review;
    }
    else{
      System.out.println("This entry is yet to be completed\n");  //Display a message
    }
  }  

  public abstract void updateProgress();

  public abstract String toString();

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

  public String getGenre()
  {
    return GENRE;
  }
}
