package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String command) throws DukeException {
        // todo: use enum and then lambda to pass in delimiter
        super(command);
        s.useDelimiter("/at");
        setDescription();
        setDeadlineString();
        setDeadline();
    }

    @Override
    public Task createTask() {
        return new Event(getDescription(), getDeadline());
    }
}
