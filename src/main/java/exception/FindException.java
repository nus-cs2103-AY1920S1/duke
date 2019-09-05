package exception;

/**
 * Responsible for problems occurred while finding a task.
 */
public class FindException extends DukeException {

    public FindException() {
        super("OOPS!!! You are not finding anything.");
    }

}
