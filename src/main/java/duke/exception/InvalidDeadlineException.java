package duke.exception;

/**
 * Represents an exception to be thrown when the name or the date of the Deadline Task is not given or valid.
 */
public class InvalidDeadlineException extends DukeException {

    public String message;

    public InvalidDeadlineException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + message;
    }

}
