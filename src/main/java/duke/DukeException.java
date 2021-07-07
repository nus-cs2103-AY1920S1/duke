package duke;

/**
 * Encapsulates a generic exception for the Duke project.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object.
     *
     * @param message  Message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}