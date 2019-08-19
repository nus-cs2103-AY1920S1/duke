abstract class DukeException extends Exception {
    public DukeException() { super(); }
    public DukeException(String message) { super(message); }
    public DukeException(String message, Throwable cause) { super(message, cause); }
    public DukeException(Throwable cause) { super(cause); }
}

class EmptyFieldException extends DukeException {
    public EmptyFieldException() { super(); }
    public EmptyFieldException(String message) { super(message); }
    public EmptyFieldException(String message, Throwable cause) { super(message, cause); }
    public EmptyFieldException(Throwable cause) { super(cause); }
}

class InvalidCommandFormatException extends DukeException {
    public InvalidCommandFormatException() { super(); }
    public InvalidCommandFormatException(String message) { super(message); }
    public InvalidCommandFormatException(String message, Throwable cause) { super(message, cause); }
    public InvalidCommandFormatException(Throwable cause) { super(cause); }
}

class InvalidIndexException extends DukeException {
    public InvalidIndexException() { super(); }
    public InvalidIndexException(String message) { super(message); }
    public InvalidIndexException(String message, Throwable cause) { super(message, cause); }
    public InvalidIndexException(Throwable cause) { super(cause); }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException() { super(); }
    public UnknownCommandException(String message) { super(message); }
    public UnknownCommandException(String message, Throwable cause) { super(message, cause); }
    public UnknownCommandException(Throwable cause) { super(cause); }
}

class DukeIOException extends DukeException {
    public DukeIOException() { super(); }
    public DukeIOException(String message) { super(message); }
    public DukeIOException(String message, Throwable cause) { super(message, cause); }
    public DukeIOException(Throwable cause) { super(cause); }
}