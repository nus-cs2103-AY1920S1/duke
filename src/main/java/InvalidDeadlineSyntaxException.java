/**
 * Represents an exception in the case of the user trying to input
 * a deadline but with an invalid syntax.
 */
public class InvalidDeadlineSyntaxException extends DukeException {
    
    /**
     * Constructs a new throwable InvalidDeadlineSyntaxException.
     * @param message Message to be displayed when the InvalidDeadlineSyntaxException is encountered.
     */
    public InvalidDeadlineSyntaxException(String message) {
        super(message);
    }
}