package duke.exception;

/**
 * Represents the exception that occurs when a task is already done but user inputs command to mark it as done again.
 */
public class DukeTaskDoneException extends DukeException{
    public DukeTaskDoneException() {
        super("OOPS!!! Task is already completed.");
    }
}
