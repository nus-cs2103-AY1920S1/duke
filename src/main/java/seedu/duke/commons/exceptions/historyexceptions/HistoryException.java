package seedu.duke.commons.exceptions.historyexceptions;

import seedu.duke.commons.exceptions.DukeException;

/**
 * Defines a public class HistoryException extends DukeException {
 object that extends the DukeException class.
 * Signals an error when a client of the Duke program is attempting to traverse through the timeline of TaskLists.
 */
public class HistoryException extends DukeException {

    /**
     * Creates an instance of public class HistoryException extends DukeException {
 that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public HistoryException(String message) {
        super(message);
    }

    /**
     * Creates an instance of public class HistoryException extends DukeException {
 that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
