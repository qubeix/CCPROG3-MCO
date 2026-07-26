package com.example.model;

public class Reviews
{
    private int[] ratings;
    private String[] comments;
    private int ratingsCount;
    private int commentsCount;

    /**
     * Accepts a maximum count for both ratings and comments, and initializes their arrays accordingly.
     * Also initializes count of ratings and count of comments to 0.
     * @param maxCounts the meximum count for the ratings and comments
     */
    public Reviews(int maxCounts)
    {
        ratings = new int[maxCounts];
        comments = new String[maxCounts];

        ratingsCount = 0;
        commentsCount = 0;
    }

    /**
     * Adds a rating to the array of ratings
     * @param rating the rating to be added
     */
    public void addRating(int rating)
    {
        ratings[ratingsCount] = rating;
        ratingsCount++;
    }

    /**
     * Adds a comment to the array of comments
     * @param comment the comment to be added
     */
    public void addComment(String comment)
    {
        comments[commentsCount] = comment;
        commentsCount++;
    }

    /**
     * Computes for the average of the ratings
     * @return the average of the ratings
     */
    public double getAverageRating()
    {
        int sum = 0;

        int i;
        for(i=0; i<ratingsCount; i++)
            sum += ratings[i];

        return sum / (ratingsCount * 1.0 + 1);
    }
}
