package exception;

/**
 * DukeIoException class.
 *
 * <p>Exception thrown when input/output problems occur in Duke.
 *
 * @author Marcus Ong
 */
public class DukeIoException extends DukeException {
    public DukeIoException() {
        super();
    }

    public DukeIoException(String message) {
        super(message);
    }

    public DukeIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeIoException(Throwable cause) {
        super(cause);
    }
}
