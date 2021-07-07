package seedu.duke.commons.exceptions.listexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a InvalidNumberFormatException object that extends the DukeException class.
 * Signals that a number keyed in as input is invalid.
 * May be used to specify that a TaskList size is invalid (eg. not a natural number) or
 * specify that the number of versions to traverse in a TaskListHistory is invalid.
 */
public class InvalidNumberFormatException extends DukeException {

    /**
     * Creates an instance of InvalidNumberFormatException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public InvalidNumberFormatException(String message) {
        super(message);
    }

    /**
     * Creates an instance of InvalidNumberFormatException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public InvalidNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
