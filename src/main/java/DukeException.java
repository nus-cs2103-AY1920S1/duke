/**
 * Represents an exception encountered by <code>Duke</code>.
 */
public class DukeException extends Exception {

    /**
     * Creates a <code>DukeException</code> from an error message.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(String.format("\u2639 OOPS!!! %s\n", message)); // Message begins with white frowning face symbol
    }
}