package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import java.util.Date;

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
