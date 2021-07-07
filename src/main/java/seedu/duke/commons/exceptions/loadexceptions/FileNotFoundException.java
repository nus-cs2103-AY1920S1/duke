package seedu.duke.commons.exceptions.loadexceptions;

/**
 * Defines a FileNotFoundException object that extends the LoadException class.
 * Signals that the file cannot be found when the program is attempting to load the file.
 */
public class FileNotFoundException extends LoadException {

    /**
     * Creates an instance of FileNotFoundException that stores the error message.
     * Takes in and stores the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    public FileNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates an instance of FileNotFoundException that stores both the error message and cause.
     * Takes in and stores the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
