package duke.exception;

public class EmptyTaskNumberException extends DukeException {
    public EmptyTaskNumberException() {
        super("\u2639 OOPS!!! Please enter a task number!\n");
    }
}
