package czkay.duke.exception;

/**
 * A DukeException for when the user inputs the time of a Deadline or Event task in an improper format.
 */
public class InvalidTimeException extends DukeException {

    public InvalidTimeException(String msg) {
        super(msg);
    }

}
