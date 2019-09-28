package duke.exception;

/**
 * DukeException takes in an error message and prints it.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
