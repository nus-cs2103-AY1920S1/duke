package seedu.duke.exceptions;

public class TaskDoesNotExistException extends DukeException {

    public TaskDoesNotExistException() {
        super("Task does not exist.");
    }
}
