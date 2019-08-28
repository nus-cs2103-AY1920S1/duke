package duke.exception;

/**
 * An exception when a to-do task does not have a description.
 */
public class EmptyTodoDscDukeException extends DukeException{
    public EmptyTodoDscDukeException(String s) {
        super(s);
    }
}
