package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Create a DeleteCommand. It removes user's task from the list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(int n) {
        super.pos = n;
    }

    /**
     * Delete a task from user list.
     *
     * @param t TaskList to be appended.
     * @param ui UI to interact with user.
     * @param storage Storage to read and write files.
     * @throws DukeException If user input a number that is not within TaskList.
     */
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = t.tasks.remove(pos);
            ui.showDeletedTask(deletedTask, t.tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a number that is within the list");
        }
    }
}
