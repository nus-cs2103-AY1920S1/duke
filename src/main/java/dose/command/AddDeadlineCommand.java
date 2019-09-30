package dose.command;

import dose.task.DeadlineTask;
import dose.task.Task;
import dose.util.exception.DoseException;

/**
 * Represents a command to create and add a DeadlineTask.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String command) throws DoseException {
        super(command);
        s.useDelimiter("/by");
        setDescription();
        setDeadlineString();
    }

    @Override
    public Task createTask() throws DoseException {
        assert this.getDescription() != null;
        assert this.getDeadlineString() != null;

        return new DeadlineTask(getDescription(), getDeadlineString());
    }
}
