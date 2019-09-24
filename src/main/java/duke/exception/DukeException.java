package duke.exception;

/**
 * Represents a class to implement a duke.exception.DukeException to throw errors related to duke.Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a duke.exception.DukeException error object so it can be thrown and caught.
     *
     * @param errorMessage to be printed when an error occurs.
     */
    public DukeException(String errorMessage) {

        super(errorMessage);

    }
}
