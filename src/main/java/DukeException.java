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
        super(String.format("OOPS!!! %s\n", message));
    }
}