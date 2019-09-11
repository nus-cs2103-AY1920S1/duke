package duke.exception;

import java.util.InputMismatchException;

/**
 * Exception for when user input is invalid
 */
public class DukeException extends InputMismatchException {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
