package duke.exception;

/**
 * DukeException representing Exceptions that arise due to user input in Duke UI
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException with an exception message
     */
    public DukeException(String message) {
        super(message);
    }
}
