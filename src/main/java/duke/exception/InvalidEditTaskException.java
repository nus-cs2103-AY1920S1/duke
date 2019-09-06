package duke.exception;

/**
 * Represents an exception to be thrown when the data given to edit the task is invalid.
 */
public class InvalidEditTaskException extends DukeException {
    public String message;

    public InvalidEditTaskException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + message;
    }
}
