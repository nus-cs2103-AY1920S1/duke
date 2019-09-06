package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to delete tasks from a task list.
 */
public class DeleteCommand extends Command {

    private int deletedItemNo;

    /**
     * Constructs a delete Command.
     *
     * @param deletedItemNo the index of the task to be deleted according to the list
     */
    public DeleteCommand(int deletedItemNo) {
        super(false);
        this.deletedItemNo = deletedItemNo;
    }

    /**
     * Deletes the task from task list.
     *
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     * @throws DukeException if index or task is not found in the task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(deletedItemNo, ui);
    }
}
