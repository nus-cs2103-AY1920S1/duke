package duke.command;

import duke.util.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import java.util.Date;

/**
 * Represents a command to create and add a Todo task.
 */
public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String fullCommand) throws DukeException {
        super(fullCommand);
        s.useDelimiter("\n"); // no special delimiter required
        setDescription();
    }

    @Override
    Date getDeadline() {
        return null;
    }

    @Override
    public Task createTask() {
        return new Todo(getDescription());
    }
}
