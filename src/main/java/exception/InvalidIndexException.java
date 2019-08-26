package exception;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException() { super(); }
    public InvalidIndexException(String message) { super(message); }
    public InvalidIndexException(String message, Throwable cause) { super(message, cause); }
    public InvalidIndexException(Throwable cause) { super(cause); }
}

