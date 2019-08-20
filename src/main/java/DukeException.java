/**
 * Encapsulates exceptions that are specific to Duke chat bot. Most of these exceptions are
 * user's input error.
 */
public class DukeException extends Exception {
    /**
     * The prefix of the error message of all DukeException objects.
     */
    public static final String PREFIX =  "\u2639  OOPS!!!";

    /**
     * Constructs a DukeException object.
     *
     * @param message the corresponding error message.
     */
    public DukeException(String message) {
        super(String.format("%s %s", PREFIX, message));
    }
}
