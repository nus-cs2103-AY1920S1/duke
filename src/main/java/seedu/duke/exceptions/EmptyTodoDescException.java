package seedu.duke.exceptions;

public class EmptyTodoDescException extends DukeException {

    public EmptyTodoDescException() {
        super("The description of a todo cannot be empty.");
    }
}
