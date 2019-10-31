package duke.exception;

/**
 * Exception class allow user to get the error message
 * of the thrown error.
 *
 * @author TeoShyanJie
 *
 */
public class DukeException extends Exception {
    /**
     * Error message of the exception class.
     * @param message Pass the error message to the super class.
     */
    public DukeException(String message) {
        super(message);
    }
}