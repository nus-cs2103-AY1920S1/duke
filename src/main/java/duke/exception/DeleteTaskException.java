package duke.exception;

public class DeleteTaskException extends DukeException {
    public DeleteTaskException() {
        super("Sorry, you don't have that task in the list to delete.");
    }
}
