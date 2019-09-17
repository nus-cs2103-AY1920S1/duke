package duke.exception;

public class UnknownInputDukeException extends DukeException {
    public UnknownInputDukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
