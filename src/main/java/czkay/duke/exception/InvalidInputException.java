package czkay.duke.exception;

/**
 * A DukeException for when the user inputs an invalid command.
 */
public class InvalidInputException extends DukeException {

    public InvalidInputException(String msg) {
        super(msg);
    }

}
