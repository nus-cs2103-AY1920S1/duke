package exception;

/**
 * Extends DukeException and thrown to handle all other generic invalid commands that Duke can't handle.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructor for InvalidInputException.
     * @param exceptionMsg informs the user of the various correct command options the user
     *                     can use and why this exception was thrown
     */
    public InvalidInputException(String exceptionMsg) {
        super(exceptionMsg);
    }
}