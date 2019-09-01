package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents an exit command that Duke will operate.
 */
public class ExitCommand implements Command {

    /**
     * Returns should the command exit.
     *
     * @return whether should exit.
     */
    @Override
    public boolean isExit() {
        return true;
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
        ArrayList<Task> list = tasks.getList();
        ui.showLeaving();
        storage.saveFile(list);
    }
}
