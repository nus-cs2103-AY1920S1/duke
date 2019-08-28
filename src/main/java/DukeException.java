/**
 * Represents a class to implement a DukeException to throw errors related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException error object so it can be thrown and caught.
     *
     * @param errorMessage to be printed when an error occurs.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
