package duke.exception;

/**
 * An exception when the task index is invalid because it is not a number or it is out of bounds.
 */
public class InvalidTaskIndexDukeException extends DukeException {
    public InvalidTaskIndexDukeException(String s) {
        super(s);
    }
}
