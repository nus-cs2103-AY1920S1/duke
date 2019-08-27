package exception;

/**
 * InvalidCommandFormatException class.
 *
 * <p>Exception thrown when invalid command formats are given in Duke.
 *
 * @author Marcus Ong
 */
public class InvalidCommandFormatException extends DukeException {
    public InvalidCommandFormatException() {
        super();
    }

    public InvalidCommandFormatException(String message) {
        super(message);
    }

    public InvalidCommandFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandFormatException(Throwable cause) {
        super(cause);
    }
}

