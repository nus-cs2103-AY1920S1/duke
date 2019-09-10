package duke.dukeinterface;

/**
 * Throws DukeException should there be any invalid commands given by the
 * user when using Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException class. It contains the error message
     * which specifies the error that occurred.
     *
     * @param message Takes in the error message which indicates the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
