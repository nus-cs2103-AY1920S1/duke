package duke.util;

/**
 * Custom DukeException to handle exception that arise purely from Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor to create a new DukeException.
     *
     * @param errorMessage description of error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
