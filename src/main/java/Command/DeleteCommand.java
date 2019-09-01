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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showDeleteMessage(taskList, taskList.list.get(taskNo - 1));
        taskList.delete(taskNo);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
