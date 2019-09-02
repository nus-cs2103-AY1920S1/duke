package seedu.duke.exceptions;

public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException() {

    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message,cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
