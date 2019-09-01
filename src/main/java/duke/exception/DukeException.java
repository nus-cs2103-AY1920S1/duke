package duke.exception;

/**
 * Represents the exceptions that will happen in duke.
 */
public class DukeException extends Exception {
    String message; //The error message.

    /**
     * Initiates object.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Turns object to string.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return message;
    }

    /**
     * Gets the error message.
     *
     * @return Error message.
     */
    public String getMessage() {
        return message;
    }
}
