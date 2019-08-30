package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class CommandWithNumber extends Command {
    protected Integer taskNumber;

    public CommandWithNumber(final Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeException {
        if (this.taskNumber == null) {
            throw new DukeException("Task number cannot be empty");
        }
        if (this.taskNumber < 1 || this.taskNumber >= tasks.size()) {
            throw new DukeException("Task does not exist");
        }
    }
}
