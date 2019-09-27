package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Command containing method for adding Task to TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     * 
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    
    /**
     * Adds a Task to the TaskList.
     *
     * @param tasks TaskList to add Task to.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        ui.printResponse("Got it. I've added this task:\n  "
                + this.task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
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