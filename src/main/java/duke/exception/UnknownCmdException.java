package duke.exception;

public class UnknownCmdException extends DukeException {
    public UnknownCmdException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
