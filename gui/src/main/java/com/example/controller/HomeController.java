package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.model.Library;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

/**
 * Controller class for the home view.
 * Displays a real-time clock and the user's library completion progress.
 */
public class HomeController {
     /** Label for displaying the current date. */
    @FXML
    private Label dateLabel;

    /** Label for displaying the current time. */
    @FXML
    private Label timeLabel;

    /** Progress bar showing the percentage of completed entries. */
    @FXML
    private ProgressBar completionBar;

    /** Label showing completion details (e.g., "3 of 5 entries completed"). */
    @FXML
    private Label completionLabel;

    /** Reference to the user's library. */
    private Library library;

     /** Timeline for updating the clock every second. */
    private Timeline clockTimeline;

    /** Formatter for displaying the date as "Day, Month Day, Year". */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
    
    /** Formatter for displaying the time as "hh:mm:ss AM/PM". */
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mm:ss a");

    /**
     * Sets the library reference for this controller
     * 
     * Initialize the home view by updating the completion progress bar and
     * displaying a real-time clock display
     * 
     * @param library the Library object containining books, album, and series
     */
    public void setLibrary(Library library) {
        this.library = library;
        updateCompletion();
        startClock();
    }

    /**
     * Updates the copletion progress bar and label based on the user's library
     * 
     * calculates the total number of entries and the number marked as "Completed"
     * dispalys the ratio as both a progress bar value and a percentage label
     * if no entries exist, dispalys a message that the library is empty
     */
    private void updateCompletion() {
        int totalEntries = library.getBookCount() + library.getAlbumCount() + library.getSeriesCount();
        int completedCount = 0;

        for (int i = 0; i < library.getBookCount(); i++) {
            if ("Completed".equalsIgnoreCase(library.getBookEntry(i).getStatus())) {
                completedCount++;
            }
        }
        for (int i = 0; i < library.getAlbumCount(); i++) {
            if ("Completed".equalsIgnoreCase(library.getAlbumEntry(i).getStatus())) {
                completedCount++;
            }
        }
        for (int i = 0; i < library.getSeriesCount(); i++) {
            if ("Completed".equalsIgnoreCase(library.getSeriesEntry(i).getStatus())) {
                completedCount++;
            }
        }

        if (totalEntries == 0) {
            completionBar.setProgress(0);
            completionLabel.setText("No entries in your library yet");
        } else {
            double ratio = (double) completedCount / totalEntries;
            completionBar.setProgress(ratio);
            int percent = (int) Math.round(ratio * 100);
            completionLabel.setText(completedCount + " of " + totalEntries + " entries completed (" + percent + "%)");
        }
    }

    /**
     * starts a timeline that updates the clock every second
     * 
     * initalize the date and time label with the current system time
     */
    private void startClock() {
        updateClock();
        clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }

    /**
     * updates the date and tiem labels with the current system time
     * 
     * formats the date as "Day, Month Day, Year" and for the tiem as "hh:mm:ss
     * AM/PM"
     */
    private void updateClock() {
        LocalDateTime now = LocalDateTime.now();
        dateLabel.setText(now.format(DATE_FORMAT));
        timeLabel.setText(now.format(TIME_FORMAT));
    }

    /**
     * Stops the clock timeline if it is running
     * 
     * this is called when the home view is closed or no longer active as this help
     * prevents unnecessary background updates
     */
    public void stopClock() {
        if (clockTimeline != null) {
            clockTimeline.stop();
        }
    }
}
