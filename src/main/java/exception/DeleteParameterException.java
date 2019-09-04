package exception;

/**
 * Extends DukeException and is an exception thrown when user does not enter an integer after
 * calling a delete command.
 */
public class DeleteParameterException extends DukeException {

    /**
     * Constructor for DeleteParameterException.
     * @param exceptionMsg informs the user what the correct format of a delete command should be and why
     *                     this exception was thrown
     */
    public DeleteParameterException(String exceptionMsg) {
        super(exceptionMsg);
    }
}