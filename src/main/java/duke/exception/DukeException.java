package duke.exception;

/**
 * This is the class for exceptions in Duke.
 */
public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}