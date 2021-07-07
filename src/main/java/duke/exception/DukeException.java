package duke.exception;

/**
 * An exception for methods performed in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     */
    public DukeException() {
        super();
    }

    /**
     * Overloaded constructor for DukeException takes in a string.
     *
     * @param message The message of the DukeException
     */
    public DukeException(String message) {
        super(message);
    }
}
