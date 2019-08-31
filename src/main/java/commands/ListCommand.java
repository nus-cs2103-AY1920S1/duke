package commands;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class ListCommand extends Command {

    /**
     * Show the user the current list of tasks.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     * @param storage to write changes to file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
