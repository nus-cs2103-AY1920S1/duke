package duke;

public abstract class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}

