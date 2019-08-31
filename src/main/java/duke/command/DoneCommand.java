package duke.command;

import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *  The <code>DoneCommand</code> is created when the user enters <code>"done"</code>. The done command will mark a
 *  specified task entered by the user as done. The user interface will display the updated information if it is
 *  successful.
 */
public class DoneCommand implements Command {

    /**
     * The index of the task to be mark as done in the list of tasks.
     */
    int index;

    /**
     * Constructs a new done command with the specified index of the task to be marked as done in the list of tasks.
     * @param index the index of the task to be mark as done in the list of tasks
     * @throws InvalidParameterException if the index of the task specified is not a number
     */
    public DoneCommand(String index) {
        try {
            this.index = Integer.parseInt(index);
        } catch(NumberFormatException nfe) {
            throw new InvalidParameterException(index);
        }
    }

    /**
     * Executes the command. This will mark the task specified by the user as done and display the updated information
     * on the user interface.
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws duke.exception.InvalidParameterException if the index is out of range
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String task = tasks.done(index);
            ui.showMarkedAsDone(task);
        } catch(IndexOutOfBoundsException aioube) {
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
