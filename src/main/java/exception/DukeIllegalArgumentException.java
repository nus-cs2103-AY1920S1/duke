package exception;

public class DukeIllegalArgumentException extends DukeException {
    public DukeIllegalArgumentException() { super(); }
    public DukeIllegalArgumentException(String message) { super(message); }
    public DukeIllegalArgumentException(String message, Throwable cause) { super(message, cause); }
    public DukeIllegalArgumentException(Throwable cause) { super(cause); }
}