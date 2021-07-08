package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeleteCommand class deletes a task.
 *
 * @author scwaterbear
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Class Constructor.
     *
     * @param index list task identifier.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.removeTask(index);
            if (!isSaved(tasks, storage)) {
                return ui.showSaveError();
            }
            return ui.showDeleteTask(t, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexError(index);
        }
    }
}
