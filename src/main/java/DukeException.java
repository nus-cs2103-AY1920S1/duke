
/**
 * Exceptions for Duke errors.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Override the toString method.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + getMessage();
    }
}