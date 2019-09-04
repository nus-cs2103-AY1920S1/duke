/**
 * Represents an exception encountered by <code>Duke</code>.
 */
public class DukeException extends Exception {
    public DukeException() {
        super("File does not exist");
    }

    /**
     * Creates a <code>DukeException</code> that prints an error message.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        System.out.println(String.format("\u2639 OOPS!!! %s\n", message));
    }
}