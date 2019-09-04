package duke.exception;

/**
 * An exception when no task index is given to Duke when checking/deleting tasks.
 */
public class NoTaskIndexDukeException extends DukeException {
    public NoTaskIndexDukeException(String s) {
        super(s);
    }
}
