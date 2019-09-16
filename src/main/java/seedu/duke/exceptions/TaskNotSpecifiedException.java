package seedu.duke.exceptions;

public class TaskNotSpecifiedException extends DukeException {

    public TaskNotSpecifiedException() {
        super("Which task is it?");
    }
}
