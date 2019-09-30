package dose.command;

import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.exception.DoseException;

public class SaveCommand implements Command {

    /**
     * Saves the current status of the task list to the disk.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        storage.save(tasks);
    }
}
