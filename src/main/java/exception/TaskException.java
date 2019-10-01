package exception;

/**
 * An exception which only occurs if an an error is encountered when a Task Command is executed.
 */
public class TaskException extends DukeException {
    public TaskException() {

    }

    @Override
    public String toString() {
        return " ☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
