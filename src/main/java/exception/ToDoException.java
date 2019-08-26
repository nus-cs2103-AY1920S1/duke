package exception;

/**
 * Responsible for problems occured during ToDo task handling.
 */
public class ToDoException extends DukeException {

    public ToDoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }

}