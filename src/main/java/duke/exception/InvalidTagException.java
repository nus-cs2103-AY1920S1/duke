package duke.exception;

/**
 * Represents the Exception to be thrown when the tag or index given is invalid or not given.
 */
public class InvalidTagException extends DukeException {
    private String message;

    public InvalidTagException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
