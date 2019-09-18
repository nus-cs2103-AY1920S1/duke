package duke.exception;

/**
 * An exception when the priority is not a valid PriorityLevel.
 */
public class InvalidPriorityDukeException extends DukeException {
    public InvalidPriorityDukeException(String s) {
        super(s);
    }
}
