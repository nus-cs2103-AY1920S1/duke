package duke.exception;

/**
 * An exception when no task index is given when it is necessary for the command.
 */
public class NoTaskIndexDukeException extends DukeException {
    public NoTaskIndexDukeException(String s) {
        super(s);
    }
}
