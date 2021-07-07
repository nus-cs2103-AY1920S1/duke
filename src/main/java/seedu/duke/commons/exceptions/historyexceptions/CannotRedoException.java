package seedu.duke.commons.exceptions.historyexceptions;

/**
 * Defines a public class CannotRedoException extends DukeException {
 object that extends the DukeException class.
 * Signals an error when a client of the Duke program encounters an exception while attempting to access newer versions 
 * of TaskLists. 
 */
public class CannotRedoException extends HistoryException {

    /**
     * Creates an instance of public class CannotRedoException extends DukeException {
 that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public CannotRedoException(String message) {
        super(message);
    }

    /**
     * Creates an instance of public class CannotRedoException extends DukeException {
 that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public CannotRedoException(String message, Throwable cause) {
        super(message, cause);
    }
}
