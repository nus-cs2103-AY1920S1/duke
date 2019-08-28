package duke.exception;

/**
 * An exception when an event does not have a description.
 */
public class EmptyEventDscDukeException extends DukeException{
    public EmptyEventDscDukeException(String s) {
        super(s);
    }
}
