package seedu.duke;

/**
 * DukeException is the base class of all exceptions thrown in Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     *
     * @param errorMessage the error message to be thrown
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
