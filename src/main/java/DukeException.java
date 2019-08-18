public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class EmptyToDoDescriptionException extends DukeException {
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}