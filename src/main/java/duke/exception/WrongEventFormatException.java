package duke.exception;

/**
 * This exception is thrown when a user's commands are not in
 * the correct format as specified by Duke.
 */
public class WrongEventFormatException extends DukeException {
    public WrongEventFormatException(String message) {
        super(message);
    }
}
