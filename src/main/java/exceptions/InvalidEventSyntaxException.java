package exceptions;

/**
 * Represents an exception in the case of the user trying to input
 * an event but with an invalid syntax.
 */
public class InvalidEventSyntaxException extends DukeException {
    
    /**
     * Constructs a new throwable Exceptions.InvalidEventSyntaxException.
     * @param message Message to be displayed when the Exceptions.InvalidEventSyntaxException is encountered.
     */
    public InvalidEventSyntaxException(String message) {
        super(message);
    }
}