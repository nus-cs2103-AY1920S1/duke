abstract class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}

class EmptyTaskDescriptionException extends DukeException {
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}