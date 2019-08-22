/**
 * Represents a custom exceptions class that extends from DukeException.
 * This exception is thrown when a task is missing its description.
 */
public class MissingDescriptionException extends DukeException {

    public MissingDescriptionException(String message) {
        super(message);
    }

}
