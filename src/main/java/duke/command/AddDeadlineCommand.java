package duke.command;

import duke.task.DeadlineTask;
import duke.task.Task;
import duke.util.exception.DukeException;

/**
 * Represents a command to create and add a DeadlineTask.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String command) throws DukeException {
        super(command);
        s.useDelimiter("/by");
        setDescription();
        setDeadlineString();
    }

    @Override
    public Task createTask() throws DukeException {
        return new DeadlineTask(getDescription(), getDeadlineString());
    }
}
