package exception;

public class InvalidCommandFormatException extends DukeException {
    public InvalidCommandFormatException() { super(); }
    public InvalidCommandFormatException(String message) { super(message); }
    public InvalidCommandFormatException(String message, Throwable cause) { super(message, cause); }
    public InvalidCommandFormatException(Throwable cause) { super(cause); }
}

