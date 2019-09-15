package com.exceptions;

import com.util.Ui;

public class DukeException extends Exception {

    private String message;
    private Ui ui;

    public DukeException(String message) {
        super(message);
        ui = new Ui();
        this.message = ui.indentMessage(message);
    }

    public String getMessage() {
        return this.message;
    }
}