package duke.exception;

/**
 * A DukeException subclass that handles errors the occur when completing a task.
 */
public class CompleteTaskException extends DukeException {
    /**
     * Constructs the exception when the task cannot be completed.
     */
    public CompleteTaskException() {
        super("Sorry, you don't have that task in the list to complete.");
    }
}
