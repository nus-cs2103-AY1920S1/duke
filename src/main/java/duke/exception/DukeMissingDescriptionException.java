package duke.exception;

/**
 * Signals that some arguments were omitted, particularly from the Task class.
 */
public class DukeMissingDescriptionException extends DukeException{
    public DukeMissingDescriptionException(String description) {
        super(description);
    }
}
