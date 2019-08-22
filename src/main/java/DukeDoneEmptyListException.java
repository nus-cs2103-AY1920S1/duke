package seedu.duke;

public class DukeDoneEmptyListException extends DukeException {

    public DukeDoneEmptyListException() {
        super("The todo list is empty.");
    }

}
