package duke.exception;

/**
 * Represents a checked exception.
 * A <code>DukeException</code> object corresponds to an exception that is thrown when user inputs are invalid or
 * have the wrong format.
 */
public class DukeException extends Exception {

    protected String message;

    /**
     * Constructor for <code>DukeException</code>.
     * @param message Message about the exception.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the message about the exception.
     * @return Exception message.
     */
    public String getMessage() {
        return message;
    }
}
