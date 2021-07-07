package seedu.duke.commons.exceptions.listexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a DuplicateTaskException object that extends the DukeException class.
 * Signals that a Task to be created already exists in the TaskList it is to be added to.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Creates an instance of DuplicateTaskException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public DuplicateTaskException(String message) {
        super(message);
    }

    /**
     * Creates an instance of DuplicateTaskException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public DuplicateTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
