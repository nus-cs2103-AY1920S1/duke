package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

/**
 * Represents the <code>Command</code> to delete task from the task list.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class DeleteCommand extends Command {

    /**
     * Represents the zero-based index of the task to be deleted from the task list.
     */
    protected int deletedTask;

    /**
     * Class constructor.
     *
     * @param deleted The zero-based index of the task to be deleted from the task list.
     */
    public DeleteCommand(int deleted) {
        super();
        this.deletedTask = deleted;
    }

    /**
     * This method allows for the execution of the delete command which deletes the stated task from the
     * <code>TaskList tasks</code>.
     *
     * @param tasks The task lists which contains all the user added tasks.
     * @param ui The user interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("The task list is empty, you have nothing to delete!");
        } else {
            ui.printRemoveMessage(tasks.delete(this.deletedTask), tasks.size());
        }
    }
}