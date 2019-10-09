package duke.exception;

/**
 * Thrown if the user attempts to access a task item using a value that is not a number.
 */
public class NotAnIntegerTaskListException extends DukeException {
    /**
     * Creates a NotAnIntegerTaskListException exception.
     *
     * @param message Message to be printed.
     */
    public NotAnIntegerTaskListException(String message) {
        super(message);
    }
}
