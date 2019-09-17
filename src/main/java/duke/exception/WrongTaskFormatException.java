package duke.exception;

/**
 * This exception is thrown when a user's commands are not in
 * the correct format as specified by Duke.
 */
public class WrongTaskFormatException extends DukeException {
    public WrongTaskFormatException(String message) {
        super(message);
    }
}