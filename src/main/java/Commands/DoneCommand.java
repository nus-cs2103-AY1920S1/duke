package Commands;

import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(taskIndex);
        storage.uploadTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
