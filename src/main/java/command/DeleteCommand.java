package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Command containing method for deleting Task from TaskList.
 */
public class DeleteCommand extends Command {
    private int itemId;

    /**
     * Constructor for DeleteCommand.
     * 
     * @param itemId Id of item to be deleted.
     */
    public DeleteCommand(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param tasks TaskList to delete Task from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task item = tasks.remove(this.itemId);
            ui.printResponse("Noted. I've removed this task:  \n  "
                + item.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no item " + this.itemId + ".");
        }   
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return false so program does not exit.
     */
    public boolean isExit() {
        return false;
    }
}