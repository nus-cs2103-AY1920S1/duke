package duke.exception;

/**
 * Inherits from abstract DukeException class
 * Handles errors related to <code>todo</code> command with no follow up todoInformation
 */
public class EmptyTodoTextException extends DukeException {

    public EmptyTodoTextException(String message) {
        super(message);
    }
}
