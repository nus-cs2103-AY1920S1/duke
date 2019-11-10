package duke.exception;

/**
 * DukeException class which can be thrown when exceptions related to Duke are raised. Will eventually be caught by
 * Duke Object.
 */
public class DukeException extends Exception {

    /**
     * Initialises a DukeException object.
     * @param errorMessage Custom error message.
     * @param err Throwable which is raised by the exception caught.
     */
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
