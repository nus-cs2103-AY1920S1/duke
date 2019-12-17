package com.duke.exception;

/**
 * Represents DukeException in general.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs a DukeException object.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
