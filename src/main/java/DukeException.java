import java.util.InputMismatchException;

/**
 * The DukeException class is used to encompass all errors regarding user input in the program.
 */
public class DukeException extends InputMismatchException {

    /**
     * Class constructor that assigns an error message to the object.
     * @param message Error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
