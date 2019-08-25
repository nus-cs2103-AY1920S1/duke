package duck.command;

import duck.task.Task;
import duck.util.DukeException;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

public class DeleteCommand extends Command {

    private int target;

    public DeleteCommand(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (target < 0 || target >= taskList.getTotalTask()) {
            throw new DukeException("The task number is invalid!");
        }
        Task removedTask = taskList.removeTaskAt(target);
        ui.showTaskDeleted(taskList.getTotalTask(), removedTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand another = (DeleteCommand) obj;
            return this.target == another.target;
        } else {
            return false;
        }
    }
}
