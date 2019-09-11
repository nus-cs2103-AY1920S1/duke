package duke.exception;

/**
 * An exception when no task index is given to Duke when checking/deleting tasks.
 */
public class NoPriorityDukeException extends DukeException {
    public NoPriorityDukeException(String s) {
        super(s);
    }
}
