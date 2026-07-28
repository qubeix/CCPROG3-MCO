package com.example.model;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String input) {
        if (input.matches("\\d*") && !(input.matches("0"))) {
            super.replaceText(start, end, input);
        }
    }

    @Override
    public void replaceSelection(String input) {
        if (input.matches("\\d*") && !(input.matches("0"))) {
            super.replaceSelection(input);
        }
    }
}
