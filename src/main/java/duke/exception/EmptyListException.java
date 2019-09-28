package duke.exception;

/**
 * A DukeException subclass that handles errors that occur when existing list is empty.
 */
public class EmptyListException extends DukeException {
    /**
     * Constructs the exception when list is empty.
     */
    public EmptyListException() {
        super("Your list is currently empty. Try telling me some tasks!");
    }
}
