package czkay.duke.exception;

/**
 * A DukeException for when the user inputs an empty task description.
 */
public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String msg) {
        super(msg);
    }

}
