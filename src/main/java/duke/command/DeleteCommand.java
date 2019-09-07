package duke.command;

import duke.tasklist.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

public class DeleteCommand extends Command {
    int deletedTaskIndex;

    public DeleteCommand(int deletedTaskIndex) {
        this.deletedTaskIndex = deletedTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(deletedTaskIndex);
        String taskDescription = deletedTask.toString();
        ui.showDeleteTaskMessage(taskDescription, taskList.getListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}