package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a delete command that Duke will operate.
 */
public class DeleteCommand implements Command {
    private int deleteIndex; //The index in tasklist that should delete.

    /**
     * Initiates object.
     *
     * @param deleteIndex The index should delete.
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Returns should the command exit.
     *
     * @return whether should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks  Tasks store in tasklist.
     * @param ui User interaction.
     * @param storage The storage area.
     * @throws DukeException  If there is mistake in operation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task removedTask = tasks.deleteTask(deleteIndex);
        ArrayList<Task> list = tasks.getList();
        ui.showDelete(removedTask, list.size());
        storage.saveFile(list);
    }
}
