/**
 * Represents all the Exceptions related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException with an error message and the
     * root error given by java.
     * @param errorMessage logic behind the error that describes the issue with Duke.
     * @param rootError root error given by java.
     */
    public DukeException(String errorMessage, Throwable rootError) {
        super(errorMessage, rootError);
    }

    /**
     * Creates a new DukeException with an error message.
     * @param errorMessage logic behind the error that describes the issue with Duke.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}