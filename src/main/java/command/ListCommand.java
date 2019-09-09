package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Shows all tasks in task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListCommand(tasks);
    }
}
