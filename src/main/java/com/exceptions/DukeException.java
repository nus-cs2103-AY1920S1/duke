package com.exceptions;

import com.util.ui.*;
import gui.GUIUi;

public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}