package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deleted = taskList.deleteTask(taskIndex);
        ui.showDelTaskMsg(deleted.toString(), taskList.getListSize());
        storage.uploadTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
