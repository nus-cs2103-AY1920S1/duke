package slave.exception;

/**
 * Represents IO Exception
 */
public class IOWentWrongException extends DukeException {

    /**
     * Constructor for IOWentWrongException
     */
    public IOWentWrongException() {
        super("Something went wrong with the IO commands");
    }
}
