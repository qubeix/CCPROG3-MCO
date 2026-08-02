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

    public void setLibrary(Library library){
        this.library = library;
        updateCompletion();
        startClock();
    }

    private void updateCompletion(){
        int totalEntries = library.getBookCount() + library.getAlbumCount() + library.getSeriesCount();
        int completedCount = 0;

        for (int i = 0; i < library.getBookCount(); i++) {
            if("Completed".equalsIgnoreCase(library.getBookEntry(i).getStatus())){
                completedCount++;
            }
        }
        for (int i = 0; i < library.getAlbumCount(); i++) {
            if("Completed".equalsIgnoreCase(library.getAlbumEntry(i).getStatus())){
                completedCount++;
            }
        }
        for (int i = 0; i < library.getSeriesCount(); i++) {
            if("Completed".equalsIgnoreCase(library.getSeriesEntry(i).getStatus())){
                completedCount++;
            }
        }

        if (totalEntries == 0){
            completionBar.setProgress(0);
            completionLabel.setText("No entries in your library yet");
        }
        else{
            double ratio = (double) completedCount / totalEntries;
            completionBar.setProgress(ratio);
            int percent = (int) Math.round(ratio * 100);
            completionLabel.setText(completedCount + " of " + totalEntries + " entries completed (" + percent + "%)");
        }
    }

    private void startClock(){
        updateClock();
        clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }

    private void updateClock(){
        LocalDateTime now = LocalDateTime.now();
        dateLabel.setText(now.format(DATE_FORMAT));
        timeLabel.setText(now.format(TIME_FORMAT));
    }

    public void stopClock(){
        if(clockTimeline != null){
            clockTimeline.stop();
        }
    }
}
