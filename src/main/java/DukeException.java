/**
 * Superclass from which all Duke-specific exceptions inherit
 */
public class DukeException extends Exception {
    /**
     * 
     * @param message Exception message
     */
    public DukeException(String message) {
        super(message);
    }
}