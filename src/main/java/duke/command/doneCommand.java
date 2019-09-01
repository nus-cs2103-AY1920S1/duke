package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command that Duke will operate.
 */
public class doneCommand implements Command {
    private int doneIndex; //The index in tasklist that should be done.

    /**
     * Initiates object.
     *
     * @param doneIndex The index should be done.
     */
    public doneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
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
        Task doneTask = tasks.doneTask(doneIndex);
        ui.showDone(doneTask);
        storage.saveFile(tasks.getList());
    }
}
