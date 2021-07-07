package exceptions;

/**
 * Represents an exception in the case the user types the date for 
 * either an event or deadline incorrectly so that it cannot be
 * understood by Core.Duke.
 */
public class InvalidDukeDateException extends DukeException {
    
    /**
     * Constructs a new throwable Exceptions.InvalidDukeDateException.
     * @param message Message to be displayed when the Exceptions.InvalidDukeCommandException is encountered.
     */
    public InvalidDukeDateException(String message) {
        super(message);
    }
}