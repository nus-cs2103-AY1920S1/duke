package seedu.duke.commons.exceptions.commandexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a EmptyCommandException object that extends the DukeException class.
 * Signals an invalid command has been received by the program.
 * This may refer to the entire command or the first word of the full command used to instruct the program.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Creates an instance of EmptyCommandException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Creates an instance of EmptyCommandException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
