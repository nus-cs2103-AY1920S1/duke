/**
 * Represents an exception specific to a Duke application.
 * A subclass of the Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructor.
     * @param message description of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
