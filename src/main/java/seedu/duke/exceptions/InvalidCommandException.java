package seedu.duke.exceptions;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {

    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "No such command found.";
    }
}
