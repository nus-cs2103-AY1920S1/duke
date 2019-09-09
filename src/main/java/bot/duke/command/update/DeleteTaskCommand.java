package bot.duke.command.update;

import bot.duke.command.Command;
import bot.duke.storage.Storage;
import bot.duke.task.Task;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

public class DeleteTaskCommand extends Command {

    /** Chosen Task index. */
    int chosenTaskNo;

    /**
     * Constructs the DeleteTaskCommand object.
     *
     * @param chosenTaskNo Chosen Task index
     */
    public DeleteTaskCommand(int chosenTaskNo) {
        this.chosenTaskNo = chosenTaskNo;
    }

    /**
     * Deletes Task.
     *
     * @param tasks   The current TaskList object
     * @param ui      The current Ui object
     * @param storage The current Storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.doDeleteTask(chosenTaskNo);
        ui.printDeleteSuccess(tasks.getTasks(), deletedTask);
    }

    /**
     * Returns whether this is an exiting command.
     *
     * @return Whether this command exits the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
