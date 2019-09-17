package duke.exception;

public class EmptyTaskNumberException extends DukeException {

    /**
     * Creates an EmptyTaskNumberException.
     */
    public EmptyTaskNumberException() {
        super("OOPS!!! Please enter a task number!\n");
    }
}
