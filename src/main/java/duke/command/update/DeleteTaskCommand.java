package duke.command.update;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {

    int chosenTaskNo;

    public DeleteTaskCommand(int chosenTaskNo) {
        this.chosenTaskNo = chosenTaskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.doDeleteTask(chosenTaskNo);
        ui.printDeleteSuccess(tasks.getTasks(), deletedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
