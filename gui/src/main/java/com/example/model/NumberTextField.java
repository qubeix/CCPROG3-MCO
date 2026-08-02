package com.example.model;

import javafx.scene.control.TextField;

/**
 * A custom TextField that only allows numetic inputs
 * Overrides replaceText and replaceSelection to filter non-digit characters
 */
public class NumberTextField extends TextField {

    /**
     * Replaces text only if the input contains digits
     * 
     * @param start the start index of replacement
     * @param end   the end index of replacement
     * @param input the text to insert
     */
    @Override
    public void replaceText(int start, int end, String input) {
        if (input.matches("\\d*")) {
            super.replaceText(start, end, input);
        }
    }

    /**
     * Replaces selected text only if the input contains digits
     * 
     * @param input the text to inserts
     */
    @Override
    public void replaceSelection(String input) {
        if (input.matches("\\d*")) {
            super.replaceSelection(input);
        }
    }
}
