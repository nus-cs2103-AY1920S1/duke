package seedu.duke.exceptions;

public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message,cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
