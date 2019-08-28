package duke.command.update;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {

    /** Chosen Task index. */
    int chosenTaskNo;

    /**
     * Constructs the DeleteTaskCommand object.
     * @param chosenTaskNo Chosen Task index
     */
    public DeleteTaskCommand(int chosenTaskNo) {
        this.chosenTaskNo = chosenTaskNo;
    }

    /**
     * Deletes Task.
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.doDeleteTask(chosenTaskNo);
        ui.printDeleteSuccess(tasks.getTasks(), deletedTask);
    }

    /**
     * Returns whether this is an exiting command.
     * @return Whether this command exits the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
