package duke.exception;

public class EmptyTaskNumberException extends DukeException {
    public EmptyTaskNumberException() {
        super("OOPS!!! Please enter a task number!\n");
    }
}
