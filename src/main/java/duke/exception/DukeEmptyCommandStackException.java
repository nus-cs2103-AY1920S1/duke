package duke.exception;

/**
 * A {@link DukeException}.
 * <p>
 * Thrown when the user tries to undo a command when no commands are left to be undone.
 */
public class DukeEmptyCommandStackException extends DukeException {

    public DukeEmptyCommandStackException(String message) {
        super(message);
    }

}
