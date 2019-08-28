package duke.util;

/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message String specifying the error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
