package duke.command;

import duke.task.DeadlineTask;
import duke.task.Task;
import duke.util.exception.DukeException;

/**
 * Represents a command to create and add a DeadlineTask.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String command) throws DukeException {
        // todo: use enum and then lambda to pass in delimiter
        super(command);
        s.useDelimiter("/by");
        setDescription();
        setDeadlineString();
        setDeadline();
    }

    @Override
    public Task createTask() {
        return new DeadlineTask(getDescription(), getDeadline());
    }
}
