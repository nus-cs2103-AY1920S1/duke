package exception;

/**
 * Extends DukeException and is thrown when there is an error updating the state file after running a command.
 */
public class UpdateStateException extends DukeException {

    /**
     * Constructor for UpdateStateException.
     * @param exceptionMsg informs the user that there was an error while updating the state file
     *                     after running a command and informs them why this exception was thrown
     */
    public UpdateStateException(String exceptionMsg) {
        super(exceptionMsg);
    }
}