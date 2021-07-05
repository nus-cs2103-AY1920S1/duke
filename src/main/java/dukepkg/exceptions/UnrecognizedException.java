package dukepkg.exceptions;

/**
 * The exception of type Unrecognized .
 */
public class UnrecognizedException extends DukeException {
    /**
     * Instantiates a new Unrecognized exception.
     *
     * @param err the error message of the exception.
     */
    public UnrecognizedException(String err){
        super(err);
    }
}
