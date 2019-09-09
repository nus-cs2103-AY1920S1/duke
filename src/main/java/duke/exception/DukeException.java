package duke.exception;

/**
 * DukeException is the super class of all custom exceptions in Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException exception.
     *
     * @param message Message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }
}
