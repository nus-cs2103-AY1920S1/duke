package duke.exception;

/**
 * Inherits from abstract DukeException class.
 * Handles errors related to <code>todo</code> command with no follow up todoInformation
 */
public class EmptyTaskTextException extends DukeException {

    public EmptyTaskTextException(String message) {
        super(message);
    }
}
