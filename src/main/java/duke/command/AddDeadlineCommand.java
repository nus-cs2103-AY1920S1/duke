package duke.command;

import duke.util.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a command to create and add a Deadline task.
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
        return new Deadline(getDescription(), getDeadline());
    }
}
