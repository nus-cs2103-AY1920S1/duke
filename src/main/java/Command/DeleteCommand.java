package Command;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {

        this.taskNo = taskNo;
    }

    /**
     * execute the command of deleting the task
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showDeleteMessage(taskList, taskList.list.get(taskNo - 1));
        taskList.delete(taskNo);
        storage.save(taskList);
    }

    /**
     * check if it is exited
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
