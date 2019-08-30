package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.UNKNOWN_COMMAND;

public class UnknownCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
        check(tasks);
        throw new DukeExecutionException(UNKNOWN_COMMAND);
    }
}
