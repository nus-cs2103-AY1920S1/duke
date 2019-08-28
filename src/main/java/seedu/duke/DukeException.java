package seedu.duke;

/**
 * Custom exception class handling exceptions unique to Duke.
 */
public class DukeException extends Exception {
    protected static String response;

    /** Constructor.*/
    public DukeException(String message, String respond) {
        super(message);
        this.response = respond;
    }
}
