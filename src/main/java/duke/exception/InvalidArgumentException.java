package duke.exception;

/**
 *  Represents InvalidArgumentException that is thrown when user input is invalid.
 * The InvalidArgumentException class extends DukeException class.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Initialises a new empty description exception.
     */
    public InvalidArgumentException() {

    }

    /**
     * Returns string representation of exception.
     *
     * @return String representationof exception.
     */
    public String getMessage() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
