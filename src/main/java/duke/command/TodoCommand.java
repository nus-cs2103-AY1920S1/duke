package duke.command;

import duke.task.Todo;

/**
 * Handles the command to add a todo to the list of tasks.
 */
public class TodoCommand extends AddCommand {

    public TodoCommand(String command) {
        super(new Todo(command));
    }

}
