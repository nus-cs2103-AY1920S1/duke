package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Command containing method for marking Task from TaskList as done.
 */
public class DoneCommand extends Command {
    private int itemId;

    /**
     * Constructor for DoneCommand.
     * 
     * @param itemId Id of item to be marked done.
     */
    public DoneCommand(int itemId) {
        this.itemId = itemId;
    }
    
    /**
     * Marks a Task from the TaskList as done.
     *
     * @param tasks TaskList to delete Task from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markAsDone(this.itemId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no item " + itemId + ".");
        }
        ui.printResponse("Nice! I've marked this task as done: \n  "
                + tasks.get(this.itemId).toString());
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