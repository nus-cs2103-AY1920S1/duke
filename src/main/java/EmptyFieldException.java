/**
 * Represents an exception that occurs when there are missing data from user's input.
 */
public class EmptyFieldException extends DukeException {
    public EmptyFieldException(String message) {
        super(message);
    }
}
