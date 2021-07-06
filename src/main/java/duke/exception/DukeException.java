package duke.exception;

/**
 * Represents all exceptional situations related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor of the duke exception.
     * @param message Message carried by the exception.
     */
    public DukeException(String message) {
        super(message);
    }

}
