package duke.command;

import duke.exception.FailedToSaveIoException;
import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

import java.io.IOException;

/**
 *  The <code>DoneCommand</code> is created when the user enters <code>"done"</code>. The done command will mark a
 *  specified task entered by the user as done. The user interface will display the updated information if it is
 *  successful.
 */
public class DoneCommand implements Command {

    /**
     * The index of the task to be mark as done in the list of tasks.
     */
    private int index;

    /**
     * Constructs a new done command with the specified index of the task to be marked as done in the list of tasks.
     * @param index the index of the task to be mark as done in the list of tasks
     * @throws InvalidParameterException if the index of the task specified is not a number
     */
    public DoneCommand(String index) throws InvalidParameterException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            throw new InvalidParameterException(index);
        }
    }

    /**
     * Executes the command. This will mark the task specified by the user as done and display the updated information
     * on the user interface.
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws duke.exception.InvalidParameterException if the index is out of range
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException {
        try {
            String task = taskManager.markTaskAsDone(index);
            storage.save(taskManager.getCurrentTaskListToSave());
            return ui.showMarkedAsDone(task);
        } catch (FailedToSaveIoException ftsioe) {
            return ui.showSaveError();
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }  catch (IOException ioe) {
            return ui.showSaveError();
        }
    }

}
