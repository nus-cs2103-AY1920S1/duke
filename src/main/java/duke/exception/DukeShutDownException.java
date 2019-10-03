package duke.exception;

/**
 * An exception class that indicates that Duke is shutting
 * down. One example is when the command parser detects
 * that the user has issued the "bye" command.
 */
public class DukeShutDownException extends DukeException {

    /**
     * Returns a DukeShutDownException object with an associated
     * message.
     * @param errorMessage message associated with the exception.
     */
    public DukeShutDownException(String errorMessage) {
        super(errorMessage);
    }
}
