package duke.command;

import duke.task.Todo;

/**
 * Class representing a command issued by the user to create an
 * Todo object. Inherits from the NewTaskCommand abstract class.
 * @see {@link NewTaskCommand} {@link Todo}
 */
public class TodoCommand extends NewTaskCommand {

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
