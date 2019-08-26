package exception;

public class DukeIOException extends DukeException {
    public DukeIOException() { super(); }
    public DukeIOException(String message) { super(message); }
    public DukeIOException(String message, Throwable cause) { super(message, cause); }
    public DukeIOException(Throwable cause) { super(cause); }
}