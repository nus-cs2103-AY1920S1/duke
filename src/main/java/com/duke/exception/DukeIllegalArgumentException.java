package com.duke.exception;

/**
 * Represents IllegalArgumentException for Duke. Thrown when encountered invalid user input.
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Constructs a DukeIllegalArgumentException object.
     *
     * @param message Exception message.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
