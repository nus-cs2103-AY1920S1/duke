package duke.command;

import duke.tasklist.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

public class DoneCommand extends Command {
    int completedTaskIndex;

    public DoneCommand(int completedTaskNum) {
        this.completedTaskIndex = completedTaskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task completedTask = taskList.completeTask(completedTaskIndex);
        String taskDescription = completedTask.toString();
        ui.showDoneMessage(taskDescription);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}