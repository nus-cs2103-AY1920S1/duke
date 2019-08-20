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

class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class EmptyDueDateException extends DukeException {
    public EmptyDueDateException(String message) {
        super(message);
    }
}

class IndexDoesNotExistException extends DukeException {
    public IndexDoesNotExistException(String message) {
        super(message);
    }
}