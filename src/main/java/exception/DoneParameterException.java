package exception;

/**
 * Extends DukeException and is an exception thrown when user does not enter an integer after
 * calling a done command
 */
public class DoneParameterException extends DukeException {

    /**
     * Constructor for DoneParameterException
     * @param exceptionMsg informs the user what the correct format of a done command should be and why
     *                     this exception was thrown
     */
    public DoneParameterException(String exceptionMsg) {
        super(exceptionMsg);
    }
}