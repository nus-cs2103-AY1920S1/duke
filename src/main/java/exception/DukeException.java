package exception;

/**
 *  Represents a general exception that is not caught by any of the other exceptions.
 */
public class DukeException extends Exception {
    private static final long serialVersionUID = 1L;

    public DukeException(String message) {
        super(message);
    }
}
