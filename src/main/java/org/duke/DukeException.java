package org.duke;

/**
 * Parent class for all Duke-specific exceptions.
 */
public class DukeException extends RuntimeException {
    public DukeException() {
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(Exception inner) {
        super(inner);
    }

    public DukeException(String message, Exception inner) {
        super(message, inner);
    }
}
