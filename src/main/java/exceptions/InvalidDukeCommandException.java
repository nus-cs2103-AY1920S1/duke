package exceptions;

/**
 * Represents an exception in the case the user types an invalid 
 * command that cannot be understood by Core.Duke.
 */
public class InvalidDukeCommandException extends DukeException {
    
    /**
     * Constructs a new throwable Exceptions.InvalidDukeCommandException.
     * @param message Message to be displayed when the Exceptions.InvalidDukeCommandException is encountered.
     */
    public InvalidDukeCommandException(String message) {
        super(message);
    }
}