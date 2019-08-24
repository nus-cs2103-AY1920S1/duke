package duck.command;

import duck.task.Task;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

public class DeleteCommand extends Command {

    private int target;

    public DeleteCommand(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task removedTask = taskList.removeTaskAt(target);
        ui.showTaskDeleted(taskList.getTotalTask(), removedTask);
    }
}
