package exception;

/**
 * is thrown when problem occurs while finding a task.
 */
public class FindException extends DukeException {

    public FindException() {
        super("OOPS!!! You are not finding anything.");
    }

}
