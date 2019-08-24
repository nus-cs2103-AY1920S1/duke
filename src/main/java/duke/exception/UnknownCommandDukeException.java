package duke.exception;

public class UnknownCommandDukeException extends DukeException {
    public UnknownCommandDukeException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

    public UnknownCommandDukeException(String message) {
        super(message);
    }
}
