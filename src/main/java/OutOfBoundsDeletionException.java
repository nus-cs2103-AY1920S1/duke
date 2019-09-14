/**
 * A DukeException for when the user attempts to delete from an index in the task list that does not exist.
 */
class OutOfBoundsDeletionException extends DukeException {

    OutOfBoundsDeletionException(String msg) {
        super(msg);
    }

}
