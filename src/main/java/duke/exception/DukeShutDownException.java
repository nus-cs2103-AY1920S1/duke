package duke.exception;

/** An exception class that indicates that Duke is shutting
 * down. One example is when the command parser detects
 * that the user had typed "bye".
 */
public class DukeShutDownException extends DukeException {

    /** Basic constructor for DukeShutDownException.
     * Accepts an error message to be stored.
     *
     * @param errorMessage
     */
    public DukeShutDownException(String errorMessage) {
        super(errorMessage);
    }
}
