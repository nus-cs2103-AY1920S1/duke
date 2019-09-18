package duke.exception;

/**
 * An exception when a to-do task does not have a description.
 */
public class EmptyDscDukeException extends DukeException {
    public EmptyDscDukeException(String s) {
        super(s);
    }
}