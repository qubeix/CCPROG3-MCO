package com.example.controller;

import com.example.model.Library;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;


public class RemoveEntryController {
    
    @FXML
    private VBox removeEntryPanel;

    @FXML
    private ComboBox<String> removeTypeCombo;

    @FXML
    private ComboBox<String> removeEntryCombo;

    @FXML
    private Label removeEntryLabel;

    @FXML
    private TextArea outputArea;

    private Library library;

    public void setLibrary(Library library){
        this.library = library;
    }

    @FXML
    private void initialize(){
        removeTypeCombo.getItems().setAll("Book", "Album", S"eries");
        removeEntryLabel.setVisible(false);
    }

    @FXML
    private void handleRemoveEntry(){
        removeTypeCombo.setValue(null);
        removeEntryCombo.getItems().clear();

        hideAllPanels();
        removeEntryPanel.setVisible(true);
        removeEntryPanel.setManaged(true);
    }

    @FXML
    private void onRemoveTypeSelected(){
        String type = removeTypeCombo.getValue();
        removeEntryCombo.getItems().clear();

        if ("Book".equals(type)) {
            
            for (int i = 0; i < library.getBookCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getBookEntry(i).getTitle());
            }
        } 
        else if ("Album".equals(type)) {
            
            for (int i = 0; i < library.getAlbumCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getAlbumEntry(i).getTitle());
            }
        } 
        else if ("Series".equals(type)) {
            
            for (int i = 0; i < library.getSeriesCount(); i++) {
                removeEntryCombo.getItems().add((i + 1) + ". " + library.getSeriesEntry(i).getTitle());
            }
        }
    }

    @FXML
    private void confrimRemoveEntry(){
        String type = removeTypeCombo.getValue();
        String selected = removeEntryCombo.getValue();

        if(type != null && selected !=null){

            int index = Integer.parseInt(selected.split("\\.")[0]) - 1;
            String removedEntry = "";

            switch(type){
                case "Book":{
                    removedEntry = library.removeBookAt(index);
                    break;
                }

                case "Album": {
                    removedEntry = library.removeAlbumAt(index);
                    break;
                }

                case "Series": {
                    removedEntry = library.removeSeriesAt(index);
                    break;
                }
            }
            removeEntryLabel.setVisible(false);
            outputArea.setVisible(true);
            outputArea.setManaged(true);
            //outputArea.setText(entry + " has been sucessfully removed");        //have it dispaly the name title and author of what ever entry is being removed
            outputArea.setText(removedEntry + "Entry has been removed");

            clearRemoveEntry(); 
        }
    }

    @FXML
    private void clearRemoveEntry(){
        removeEntryPanel.setVisible(false);
        removeEntryPanel.setManaged(false);

        removeTypeCombo.setValue(null);
        removeTypeCombo.getItems().clear();
    }

}
