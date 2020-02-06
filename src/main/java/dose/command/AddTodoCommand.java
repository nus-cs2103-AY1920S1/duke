package dose.command;

import dose.task.Task;
import dose.task.TodoTask;
import dose.util.exception.DoseException;

/**
 * Represents a command to create and add a TodoTask.
 */
public class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String fullCommand) throws DoseException {
        super(fullCommand);
        s.useDelimiter("\n"); // no special delimiter required
        setDescription();
    }

    @Override
    public Task createTask() {
        return new TodoTask(getDescription());
    }
}
