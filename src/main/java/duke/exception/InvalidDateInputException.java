package duke.exception;

/**
 * Represents the Exception to be thrown when the date given is not of proper format.
 */
public class InvalidDateInputException extends DukeException {

    private String message;

    public InvalidDateInputException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
