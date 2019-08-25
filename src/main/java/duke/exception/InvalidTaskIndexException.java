package duke.exception;

/**
 * Rerpresents an Exception to be thrown when an invalid task index has been given.
 */
public class InvalidTaskIndexException extends DukeException {

    public String toString() {
        return "OOPS!!! Please key in a valid task index!";
    }

}
