package exception;

/**
 * Exception where the user does not enter anything after a command.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
