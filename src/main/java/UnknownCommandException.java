/**
 * UnknownCommandExceptions represents commands not recognised by Duke.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}