package duke.exception;

/**
 * A {@link DukeException}.
 * <p>
 * Thrown when the user tries to undo a command when no commands are left to be undone.
 */
public class DukeEmptyUndoStackException extends DukeException {

    public DukeEmptyUndoStackException(String message) {
        super(message);
    }

}
