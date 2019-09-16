/**
 * Represents an exception in the case the user types an invalid 
 * command that cannot be understood by Duke.
 */
public class InvalidDukeCommandException extends DukeException {
    
    /**
     * Constructs a new throwable InvalidDukeCommandException.
     * @param message Message to be displayed when the InvalidDukeCommandException is encountered.
     */
    public InvalidDukeCommandException(String message) {
        super(message);
    }
}