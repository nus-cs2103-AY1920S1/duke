package duke.commands;

import duke.model.Model;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * list all tasks in the task list.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) {
        TaskList taskList = model.getTaskList();
        if (taskList.getSize() == 0) {
            ui.showNoTask();
        } else {
            ui.showTasks(taskList);
        }
    }
}
