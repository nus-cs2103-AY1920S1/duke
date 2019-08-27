public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

/**
 * EmptyToDoDescriptionException represents exceptions when a ToDo description is empty
 */
class EmptyToDoDescriptionException extends DukeException {
    public EmptyToDoDescriptionException(String message) {
        super(message);
    }
}

/**
 * UnknownCommandExceptions represents commands not recognised by Duke
 */
class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}