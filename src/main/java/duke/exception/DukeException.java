package duke.exception;

/**
 * Represents an exception thrown by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    } // End method.

    /**
     * Returns the error message.
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    } // End method.
}
