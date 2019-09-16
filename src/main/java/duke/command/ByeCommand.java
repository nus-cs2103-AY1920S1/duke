package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that should exit Duke.
 */
public class ByeCommand extends Command {
    @Override
    protected void check(TaskList tasks) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
