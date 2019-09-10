package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the command to show the list of all tasks.
 */
public class ShowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
