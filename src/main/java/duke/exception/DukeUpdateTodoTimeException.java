package duke.exception;

/**
 * Represents an exception where the user tries to update the time of Todo task which is not applicable.
 */
public class DukeUpdateTodoTimeException extends DukeException{

    /**
     * Creates a DukeUpdateTodoTimeException.
     */
    public DukeUpdateTodoTimeException() {
        super("A Todo task cannot update its time!");
    }
}
