package duke.exception;

/**
 * Represents the exception that occurs when command is known but there is missing information or information is formatted incorrectly.
 */
public class DukeMissingDescriptionException extends DukeException {
    public DukeMissingDescriptionException(String eventType) {
        super("OOPS!!! The description of a " + eventType + " has missing information.");
    }
}

