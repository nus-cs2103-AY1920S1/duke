package Command;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {

        this.taskNo = taskNo;
    }

    /**
     * execute the command of marking a task as done
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.list.get(taskNo - 1).markAsDone();
        ui.showDoneMessage(taskList.list.get(taskNo - 1));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
