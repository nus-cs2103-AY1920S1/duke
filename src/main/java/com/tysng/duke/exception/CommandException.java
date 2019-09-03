package com.tysng.duke.exception;

/**
 * This exception is thrown during the parsing of command line input to Command class.
 */
public class CommandException extends IllegalArgumentException {
    public CommandException(String message) {
        super(message);
    }
}
