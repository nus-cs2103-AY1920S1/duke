package seedu.duke.commons.exceptions.storageexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a SavingUnsuccessfulException object that extends the LoadException class.
 * Signals an exception that occurred while the program is attempting to save data into the Storage file.
 */
public class SavingUnsuccessfulException extends DukeException {

    /**
     * Creates an instance of SavingUnsuccessfulException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public SavingUnsuccessfulException(String message) {
        super(message);
    }

    /**
     * Creates an instance of SavingUnsuccessfulException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public SavingUnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
