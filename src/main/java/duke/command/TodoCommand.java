package duke.command;

import duke.task.Todo;

/**
 * Class representing a command issued by the user to create an
 * Todo object. Inherits from the NewTaskCommand abstract class.
 * @see NewTaskCommand
 * @see Todo
 */
public class TodoCommand extends NewTaskCommand {

    public static final String KEYWORD = "todo";

    /**
     * Returns a TodoCommand object that can be executed to
     * create a Todo object and add it to the current TaskList.
     *
     * @param description description of Todo object to be created
     */
    public TodoCommand(String description) {
        super(new Todo(description));
    }
}
