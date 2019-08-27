package duke.commands;

import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * List all tasks in the task list.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            ui.showNoTask();
        } else {
            ui.showTasks(taskList);
        }
    }
}
