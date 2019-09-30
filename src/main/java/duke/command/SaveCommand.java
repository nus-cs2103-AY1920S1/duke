package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.exception.DukeException;

public class SaveCommand implements Command {

    /**
     * Saves the current status of the task list to the disk.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
    }
}
