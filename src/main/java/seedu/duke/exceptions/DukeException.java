package seedu.duke.exceptions;

/**
 * Top Level abstraction of all Duke-Specific Exceptions.
 */
public class DukeException extends Exception {

    public DukeException() {
        super();
    }

    public  DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
