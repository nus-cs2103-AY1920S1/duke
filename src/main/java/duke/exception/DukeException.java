package duke.exception;

/**
 * The exception used in the Duke Program to indicate the program did not run correctly.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        assert getMessage() != null;

        return "     ☹ OOPS!!! " + getMessage();
    }
}