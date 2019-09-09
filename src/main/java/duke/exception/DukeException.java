package duke.exception;

/**
 * Represents an exception from duke.Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}
