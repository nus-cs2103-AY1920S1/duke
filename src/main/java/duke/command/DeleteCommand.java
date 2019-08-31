package duke.command;

import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *  The <code>DeleteCommand</code> is created when the user enters <code>"delete"</code>. The dolete command will delete
 *  a specified task entered by the user. The user interface will display the information of the deleted task if
 *  successful.
 */
public class DeleteCommand implements Command {

    /**
     * This is the index of task to be deleted in the list of tasks.
     */
    int index;

    /**
     * Constructs a new delete command with the specified index of the task to be deleted in the list of tasks.
     * @param index the index of the task to be deleted in the list of tasks
     * @throws InvalidParameterException if the index of the task specified is not a number
     */
    public DeleteCommand(String index) {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            throw new InvalidParameterException(index);
        }
    }

    /**
     * Executes the command. This will delete the specified task entered by the user from the list of tasks
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws duke.exception.InvalidParameterException if the index is out of range
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String task = tasks.delete(index);
            ui.showDeletedMessage(task, tasks.size());
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }

}
