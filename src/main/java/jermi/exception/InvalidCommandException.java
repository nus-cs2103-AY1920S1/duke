package jermi.exception;

import static jermi.misc.Constant.EXCEPTION_INVALID_COMMAND_MESSAGE;

/**
 * An exception associated with invalid command given.
 */
public class InvalidCommandException extends JermiException {
    /**
     * Public constructor for class.
     */
    public InvalidCommandException() {
        super(EXCEPTION_INVALID_COMMAND_MESSAGE);
    }
}
