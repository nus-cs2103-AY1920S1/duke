package duke.exception;

/**
 * A DukeException subclass that handles errors during deletion of tasks.
 */
public class DeleteTaskException extends DukeException {
    /**
     * Constructs the exception thrown during deletion of tasks.
     */
    public DeleteTaskException() {
        super("Sorry, you don't have that task in the list to delete.");
    }
}
