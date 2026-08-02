package com.example.controller;

import com.example.model.Library;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeController {
    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private ProgressBar completionBar;

    @FXML
    private Label completionLabel;

    private Library library;
    private Timeline clockTimeline;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
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
