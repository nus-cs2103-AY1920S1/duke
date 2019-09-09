package duke.exception;

/**
 * Represents an InvalidTodoException which is thrown when user's 'Todo'
 * input does not have a description.
 */
public class InvalidTodoDescription extends DukeException {
    public InvalidTodoDescription() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
