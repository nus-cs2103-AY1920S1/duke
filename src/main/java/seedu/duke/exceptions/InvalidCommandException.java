package seedu.duke.exceptions;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("No such command found.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
