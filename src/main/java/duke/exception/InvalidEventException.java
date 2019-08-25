package duke.exception;
/**
 * Represents an exception to be thrown when the name or the date of the Event Task is not given or valid.
 */
public class InvalidEventException extends DukeException {

    public String message;

    public InvalidEventException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + message;
    }

}