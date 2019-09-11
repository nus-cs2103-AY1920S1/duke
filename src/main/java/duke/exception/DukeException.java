package duke.exception;

/**
 * An abstract class meant to be inherited by the various exceptions related to Duke operations
 * e.g., InvalidCommandException, EmptyTodoTextException.
 */
public abstract class DukeException extends Exception {

    /**
     * Constructor for DukeExceptionClass.
     *
     * @param message A String containing information about the error
     */
    public DukeException(String message) {
        super(message);
    }
}




