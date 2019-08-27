package exception;

/**
 * UnknownCommandException class.
 *
 * <p>Exception thrown when command given is unknown in Duke.
 *
 * @author Marcus Ong
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super();
    }

    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCommandException(Throwable cause) {
        super(cause);
    }
}

