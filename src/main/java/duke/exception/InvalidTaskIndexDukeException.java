package duke.exception;

/**
 * An exception when an invalid task index is given to Duke when checking/deleting tasks.
 */
public class InvalidTaskIndexDukeException extends DukeException{
    public InvalidTaskIndexDukeException(String s) {
        super(s);
    }
}
