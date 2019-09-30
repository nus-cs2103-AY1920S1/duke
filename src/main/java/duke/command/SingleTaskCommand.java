package duke.command;

import duke.exception.DukeInvalidCommandException;
import duke.task.TaskList;

import static duke.ui.Messages.MISSING_TASK_NUMBER;
import static duke.ui.Messages.TASK_DOES_NOT_EXIST;

/**
 * Command that manipulates a single Task.
 */
public abstract class SingleTaskCommand extends Command {
    protected final Integer taskNumber;

    public SingleTaskCommand(final Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeInvalidCommandException {
        if (this.taskNumber == null) {
            throw new DukeInvalidCommandException(MISSING_TASK_NUMBER);
        }
        if (this.taskNumber < 0 || this.taskNumber >= tasks.size()) {
            throw new DukeInvalidCommandException(TASK_DOES_NOT_EXIST);
        }
    }
}
