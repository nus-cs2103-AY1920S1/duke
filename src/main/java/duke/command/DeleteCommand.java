package duke.command;

import duke.exception.FailedToSaveIOException;
import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 *  The <code>DeleteCommand</code> is created when the user enters <code>"delete"</code>. The delete command will delete
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
    public DeleteCommand(String index) throws InvalidParameterException{
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
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws InvalidParameterException {
        try {
            String task = tasks.delete(index);
            storage.save(tasks.save());
            return ui.showDeletedMessage(task, tasks.size());
        } catch (FailedToSaveIOException ftsioe) {
            return ui.showSaveError();
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }
    }

}
