package duke.exception;

/**
 * Represents an Exception which is thrown during the running of the <code>Duke</code>.
 */
public class DukeException extends Exception {

    /**
     * Class constructor specifying the error message to be displayed to the user.
     *
     * @param message The error message to be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
