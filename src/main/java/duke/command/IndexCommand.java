package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List all the tasks in the task list.
 */
public class IndexCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showIndexMsg(tasks);
    }
}