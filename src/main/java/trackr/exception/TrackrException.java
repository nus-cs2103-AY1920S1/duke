package trackr.exception;

import java.util.InputMismatchException;

/**
 * The TrackrException class is used to encompass all errors regarding user input in the program.
 */
public class TrackrException extends InputMismatchException {

    /**
     * Class constructor that assigns an error message to the object.
     * @param message Error message to be displayed.
     */
    public TrackrException(String message) {
        super(message);
    }
}
