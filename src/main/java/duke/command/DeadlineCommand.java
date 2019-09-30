package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

import static duke.ui.Messages.DEADLINE_MISSING_TIME;

/**
 * Command that adds a new deadline task.
 */
public class DeadlineCommand extends AddTaskWithTimeCommand {
    public DeadlineCommand(final String description, final LocalDateTime time) {
        super(description, time);
    }

    @Override
    protected void check(final TaskList tasks) throws DukeInvalidCommandException {
        super.check(tasks);
        if (this.time == null) {
            throw new DukeInvalidCommandException(DEADLINE_MISSING_TIME);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
        check(tasks);
        addTask(new Deadline(this.description, this.time), tasks, ui, storage);
    }
}
