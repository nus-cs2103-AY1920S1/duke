package duke.command;

import duke.exception.FailedToSaveIoException;
import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.UserInterface;

import java.io.IOException;

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
    public DeleteCommand(String index) throws InvalidParameterException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            throw new InvalidParameterException(index);
        }
    }

    /**
     * Executes the command. This will delete the specified task entered by the user from the list of tasks
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws duke.exception.InvalidParameterException if the index is out of range
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException {
        try {
            Task task = taskManager.deleteFromTaskList(index);
            taskManager.deleteFromSchedule(task);
            storage.save(taskManager.getCurrentTaskListToSave());
            return ui.showDeletedMessage(task.toString(), taskManager.getTaskListSize());
        } catch (FailedToSaveIoException ftsioe) {
            return ui.showSaveError();
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }  catch (IOException ioe) {
            return ui.showSaveError();
        }
    }

}
