package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * List all the tasks in the task list.
 */
public class IndexCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showIndexMsg(tasks);
    }
}