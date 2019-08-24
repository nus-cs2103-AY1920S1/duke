public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


class EmptyTodoTextException extends DukeException {
    public EmptyTodoTextException(String message) {
        super(message);
    }
}


class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class TaskDoesNotExistException extends DukeException {
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
