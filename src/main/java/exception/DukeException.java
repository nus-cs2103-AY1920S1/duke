package exception;

/**
 * DukeException Class.
 *
 * <p>Parent class for all exceptions in Duke.
 *
 * @author Marcus Ong
 */
public abstract class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }
}