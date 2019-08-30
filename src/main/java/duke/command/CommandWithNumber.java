package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

import static duke.ui.Messages.MISSING_TASK_NUMBER;
import static duke.ui.Messages.TASK_DOES_NOT_EXIST;

public abstract class CommandWithNumber extends Command {
    protected Integer taskNumber;

    public CommandWithNumber(final Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeException {
        if (this.taskNumber == null) {
            throw new DukeException(MISSING_TASK_NUMBER);
        }
        if (this.taskNumber < 1 || this.taskNumber >= tasks.size()) {
            throw new DukeException(TASK_DOES_NOT_EXIST);
        }
    }
}
