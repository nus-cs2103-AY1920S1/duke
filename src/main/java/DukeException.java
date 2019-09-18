/**
 * Superclass from which all Duke-specific exceptions inherit.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with a specific message.
     * 
     * @param message Exception message
     */
    public DukeException(String message) {
        super(message);
    }
}