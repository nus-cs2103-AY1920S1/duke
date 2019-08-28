package duke.exception;

/**
 * An exception when no date is provided in a deadline/event task.
 */
public class NoDateDukeException extends DukeException{
    public NoDateDukeException(String s) {
        super(s);
    }
}
