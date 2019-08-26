package seedu.duke;

/**
 * Represents the exception specific to duke.
 */
public class DukeException extends Exception {
    private String errMsg;

    /**
     * Constructor of DukeException that takes in a string error message.
     *
     * @param errMsg String of exception message that Duke should let the user know.
     */
    public DukeException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    /**
     * Constructor of DukeException.
     */
    public DukeException() {
    }

    /**
     * String representation of the duke exception.
     *
     * @return Error message of the duke exception.
     */
    @Override
    public String toString() {
        return this.errMsg;
    }

}
