package duke.exception;

/**
 * Represents exception which arises from errors when running Duke.
 */
public class DukeException extends Exception {
    /**
     * Initializes the DukeException object with a message.
     * @param message message describing the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
