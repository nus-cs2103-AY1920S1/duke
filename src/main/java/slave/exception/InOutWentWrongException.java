package slave.exception;

/**
 * Represents IO Exception.
 */
public class InOutWentWrongException extends DukeException {

    /**
     * Constructor for IOWentWrongException.
     */
    public InOutWentWrongException() {
        super("Something went wrong with the IO commands");
    }
}
