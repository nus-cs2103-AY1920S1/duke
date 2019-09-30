package dose.command;

import dose.util.exception.DoseException;
import dose.task.EventTask;
import dose.task.Task;

/**
 * Represents a command to create and add an Event task.
 */
public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String command) throws DoseException {
        super(command);
        s.useDelimiter("/at");
        setDescription();
        setDeadlineString();
    }

    @Override
    public Task createTask() throws DoseException {
        assert this.getDescription() != null;
        assert this.getDeadlineString() != null;

        return new EventTask(getDescription(), getDeadlineString());
    }
}
