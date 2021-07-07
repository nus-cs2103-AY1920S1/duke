package duke.exception;

/**
 * An Exception class specific to the Duke application.
 */
public class DukeException extends Exception {

    private String message;

    DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
