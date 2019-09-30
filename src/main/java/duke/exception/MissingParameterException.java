package duke.exception;

/**
 * The class handles errors to do with valid commands but incomplete parameters.
 */
public class MissingParameterException extends DukeException {

    /**
     * Public constructor for MissingParameterException.
     *
     * @param message The error message.
     */
    public MissingParameterException(String message) {
        super(message);
    }
}
