package Command;

import Storage.Storage;
import TaskList.TaskList;
import TaskList.Task;
import Ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {

        this.task = task;
    }

    /**
     * execute the command of adding task
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addList(task);
        ui.showAddedMessage(taskList, task);
        storage.save(taskList);
    }

    /**
     *
     * @return boolean
     *
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
