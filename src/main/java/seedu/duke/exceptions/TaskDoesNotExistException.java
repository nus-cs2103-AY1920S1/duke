package seedu.duke.exceptions;

public class TaskDoesNotExistException extends DukeException {

    public TaskDoesNotExistException() {
        super(" OOPS!!! Task does not exist.");
    }
}
