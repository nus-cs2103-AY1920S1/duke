package seedu.duke.exceptions;

/**
 * Exception class for Duke-specific errors.
 */
public class DukeException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for exception with a message.
     *
     * @param message Message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overloaded constructor that does not take in a message.
     */
    public DukeException() { }

}