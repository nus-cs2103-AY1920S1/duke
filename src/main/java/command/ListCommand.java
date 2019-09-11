package duke.command;

import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;

/**
 * Command containing method for listing Tasks in TaskList.
 */
public class ListCommand extends Command {
    /**
     * List Tasks in TaskList.
     *
     * @param tasks TaskList of program.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage of program.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list:\n" 
                + tasks.toString());
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