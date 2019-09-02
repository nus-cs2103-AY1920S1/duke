package duke.exception;

/**
 * A {@link DukeException}.
 * Thrown when the index in the {@link duke.module.TaskList} is out of bounds.
 */
public class DukeIllegalIndexException extends DukeException {

    public DukeIllegalIndexException(String message) {
        super(message);
    }

}
