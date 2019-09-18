package duke.exception;

/**
 * An exception when no priority when trying to set a task's priority.
 */
public class NoPriorityDukeException extends DukeException {
    public NoPriorityDukeException(String s) {
        super(s);
    }
}
