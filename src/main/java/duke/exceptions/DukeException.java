package duke.exceptions;

/**
 * Implements a custom exception for handling Duke-specific exceptions.
 * @author Lim Yong Shen, Kevin
 */
public class DukeException extends Exception {

    /**
     * Constructs a Duke exception with the specified error message.
     * @param errorMessage The specified error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
