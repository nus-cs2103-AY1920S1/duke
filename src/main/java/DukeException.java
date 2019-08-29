/**
 * Represents an Exception that is solely for this program.
 */
class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param message Message of the error.
     */
    public DukeException(String message) {
        super(message);
    }
}