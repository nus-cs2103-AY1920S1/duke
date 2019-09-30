package duke.command;

import duke.util.exception.DukeException;
import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents a command to create and add an Event task.
 */
public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String command) throws DukeException {
        super(command);
        s.useDelimiter("/at");
        setDescription();
        setDeadlineString();
    }

    @Override
    public Task createTask() throws DukeException {
        assert this.getDescription() != null;
        assert this.getDeadlineString() != null;

        return new EventTask(getDescription(), getDeadlineString());
    }
}
