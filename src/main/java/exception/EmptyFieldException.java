package exception;

/**
 * EmptyFieldException class.
 *
 * <p>Exception thrown when required command fields are empty in Duke.
 *
 * @author Marcus Ong
 */
public class EmptyFieldException extends DukeException {
    public EmptyFieldException() {
        super();
    }

    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFieldException(Throwable cause) {
        super(cause);
    }
}

