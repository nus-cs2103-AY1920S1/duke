/**
 * Main application exception for handling all unchecked exceptions
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException based on caught exceptions
     * @param message Exception message
     */
    public DukeException(String message) {
        super(message);
    }
}
