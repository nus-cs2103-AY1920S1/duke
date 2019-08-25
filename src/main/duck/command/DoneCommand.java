package duck.command;

import duck.task.Task;
import duck.util.DukeException;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

public class DoneCommand extends Command {

    private int target;

    public DoneCommand(int target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (target < 0 || target >= taskList.getTotalTask()) {
            throw new DukeException("The task number is invalid!");
        }
        Task doneTask = taskList.getTaskAt(target);
        doneTask.markDone();
        ui.showTaskDone(doneTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            DoneCommand another = (DoneCommand) obj;
            return this.target == another.target;
        } else {
            return false;
        }
    }
}
