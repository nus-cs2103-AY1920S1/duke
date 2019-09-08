package duke.exception;

/**
 * Encapsulates exceptions that are specific to duke.Duke chat bot. Most of these exceptions are
 * user's input error.
 */
public class DukeException extends Exception {
    /**
     * A prefix used when displaying the error message of DukeException objects.
     */
    private static final String PREFIX =  "\u2639  OOPS!!!"; // The unicode is a sad face symbol.

    /**
     * Constructs a DukeException object with the message given.
     *
     * @param message the error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException object with the message and cause given.
     *
     * @param message the error message.
     * @param cause the cause of this DukeException.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns the detail message string of this DukeException.
     *
     * @return the detail message of this DukeException instance.
     */
    @Override
    public String getMessage() {
        return String.format("%s %s", PREFIX, super.getMessage());
    }
}
