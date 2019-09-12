package duke.exception;

/**
 * DukeException means an Exception thrown when error occurs in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs the DukeException when error occurs.
     *
     * @param msg The message explaining the error.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
