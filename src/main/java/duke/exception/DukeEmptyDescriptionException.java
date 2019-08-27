package duke.exception;

/**
 * Represents the exception that occurs when the command is known but there is no information.
 */
public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException(String eventType) {
        super("OOPS!!! The description of a " + eventType + " cannot be empty.");
    }
}
