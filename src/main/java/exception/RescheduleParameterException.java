package exception;

/**
 * Extends DukeException and is an exception thrown when user does not enter an integer after
 * calling a delete command.
 */
public class RescheduleParameterException extends DukeException {

    /**
     * Constructor for RescheduleParameterException.
     * @param exceptionMsg informs the user what the correct format of a delete command should be and why
     *                     this exception was thrown
     */
    public RescheduleParameterException(String exceptionMsg) {
        super(exceptionMsg);
    }
}