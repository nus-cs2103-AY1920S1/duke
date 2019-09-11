package exceptions;

/**
 * A specific type of Exception thrown for all exceptions that
 * occur in the Duke application.
 */
public class DukeException extends Exception {
    /**
     * The exception message to be printed to user.
     */
    protected String message;

    /**
     * Constructs and initializes the attributes of a new DukeException object.
     * @param message The message to be shown by the exception.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message to be printed.
     * @return Returns the exception message.
     */
    public String getMessage() {
        return String.format("☹ OOPS!!! %s", message);
    }
}

