package duke.exception;

/**
 * Exceptions used specifically for Duke errors.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(" OOPS!!! " + message);
    }
}
