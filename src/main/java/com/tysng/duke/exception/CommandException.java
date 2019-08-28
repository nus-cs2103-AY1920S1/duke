package com.tysng.duke.exception;

public class CommandException extends IllegalArgumentException {
    public CommandException(String message) {
        super(message);
    }
}
