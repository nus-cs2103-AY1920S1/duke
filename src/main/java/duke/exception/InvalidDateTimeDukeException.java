package duke.exception;

/**
 * Represents an exception when task has invalid date time.
 */
public class InvalidDateTimeDukeException extends Exception {
    public InvalidDateTimeDukeException(String message) {
        super(message);
    }
}