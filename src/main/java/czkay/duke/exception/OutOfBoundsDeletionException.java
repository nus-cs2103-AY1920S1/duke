package czkay.duke.exception;

/**
 * A DukeException for when the user attempts to delete from an index in the task list that does not exist.
 */
public class OutOfBoundsDeletionException extends DukeException {

    public OutOfBoundsDeletionException(String msg) {
        super(msg);
    }

}
