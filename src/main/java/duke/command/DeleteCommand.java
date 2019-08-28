package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Delete extends Command {
    private int taskNumberToDelete;

    public Delete(String command, Task pending, int taskNumberToDelete){
        super(command, pending);
        this.taskNumberToDelete = taskNumberToDelete;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = list.deleteTask(taskNumberToDelete - 1);
        storage.save();
        ui.showDeletedTask();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
