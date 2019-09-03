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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task deleted = taskList.deleteTask(taskIndex);
        storage.uploadTasksToFile(taskList.getTasks());
        return ui.showDelTaskMsg(deleted.toString(), taskList.getListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
