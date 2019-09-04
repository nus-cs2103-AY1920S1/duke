package duke;

/**
 * This class is used for all Duke-related exceptions. Exception messages
 * play an important role in providing users with feedback upon invalid input.
 */
public class DukeException extends Exception {

    /**
     * Creates an exception specific to the Duke application.
     *
     * @param errorMessage Message to be passed using the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}