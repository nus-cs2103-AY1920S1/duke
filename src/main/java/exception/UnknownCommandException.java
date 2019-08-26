package exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() { super(); }
    public UnknownCommandException(String message) { super(message); }
    public UnknownCommandException(String message, Throwable cause) { super(message, cause); }
    public UnknownCommandException(Throwable cause) { super(cause); }
}

