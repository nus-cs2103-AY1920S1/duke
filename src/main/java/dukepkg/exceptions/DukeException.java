package dukepkg.exceptions;

/**
 * The exception of type Duke. Includes FormatException and UnrecognizedException.
 */
public class DukeException extends Exception{
    /**
     * Instantiates a new Duke exception.
     *
     * @param err the error message of the exception.
     */
    DukeException(String err) {
        super(err);
    }
}
