package duke.exception;

/**
 * An exception when no task index is given to Duke when checking/deleting tasks.
 */
public class InvalidPriorityDukeException extends DukeException {
    public InvalidPriorityDukeException(String s) {
        super(s);
    }
}
