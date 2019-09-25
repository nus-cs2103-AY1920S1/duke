package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DoneCommand class sets a task to be done.
 *
 * @author scwaterbear
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Class Constructor.
     *
     * @param index list task identifier.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command and persists state to disk.
     *
     * @param tasks   list of tasks.
     * @param ui      ui.
     * @param storage storage.
     * @throws DukeException If there is no such task in tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.setIsDone(index);
            if (!persistState(tasks, storage)) {
                return ui.showSaveError();
            }
            return ui.showDoneTask(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexError(index);
        }
    }
}
