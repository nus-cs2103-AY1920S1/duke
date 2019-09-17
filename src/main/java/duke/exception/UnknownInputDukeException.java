package duke.exception;

public class UnknownInputDukeException extends DukeException {

    /**
     * Creates an UnknownInputDukeException.
     */
    public UnknownInputDukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
