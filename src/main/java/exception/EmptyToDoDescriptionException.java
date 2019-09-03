package exception;

/**
 * Extends DukeException and thrown when user types ("todo") therefore attempting to create a todo
 * with any description
 */
public class EmptyToDoDescriptionException extends DukeException {
    /**
     * Constructor for EmptyToDoDescriptionException
     * @param exceptionMsg informs the user what the correct format of creating a new todo should be and why
     *      *                     this exception was thrown
     */
    public EmptyToDoDescriptionException(String exceptionMsg) {
        super(exceptionMsg);
    }
}