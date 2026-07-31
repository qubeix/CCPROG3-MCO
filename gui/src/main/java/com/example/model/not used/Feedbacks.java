package com.example.model;

public class Feedbacks {
    private int[] ratings;
    private String[] comments;
    // private int ratingsCount;
    // private int commentsCount;

    /**
     * Accepts a maximum count for both ratings and comments, and initializes their
     * arrays accordingly.
     * Also initializes count of ratings and count of comments to 0.
     * 
     * @param maxCounts the meximum count for the ratings and comments
     */
    public Feedbacks(int maxCounts) {
        ratings = new int[maxCounts];
        comments = new String[maxCounts];

        // ratingsCount = 0;
        // commentsCount = 0;
    }

    // /**
    // * Adds a rating to the array of ratings
    // *
    // * @param rating the rating to be added
    // * @param count the number of media entries
    // */
    // public void addRating(int rating, int count) {
    // ratings[count] = rating;
    // // ratingsCount++;
    // }

    // /**
    // * Adds a comment to the array of comments
    // *
    // * @param comment the comment to be added
    // * @param count the number of media entries
    // */
    // public void addComment(String comment, int count) {
    // comments[count] = comment;
    // // commentsCount++;
    // }

    /**
     * Computes for the average of the ratings
     * 
     * @param count the number of media entries
     * @return the average of the ratings
     */
    public double getAverageRating(int count) {
        int sum = 0;

        int i;
        for (i = 0; i < count; i++)
            sum += ratings[i];

        return sum / (count * 1.0 + 1);
    }
}