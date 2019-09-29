package duke.command;

import duke.task.Task;
import duke.task.TodoTask;
import duke.util.exception.DukeException;
import java.util.Date;

/**
 * Represents a command to create and add a TodoTask.
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
        return new TodoTask(getDescription());
    }
}
