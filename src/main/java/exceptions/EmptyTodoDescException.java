package exceptions;

public class EmptyTodoDescException extends DukeException {

    public EmptyTodoDescException() {
        super(" ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
