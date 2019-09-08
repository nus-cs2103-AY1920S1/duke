package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a "list" command.
 */
public class ListCommand extends Command {
    /**
     * Prints out all the tasks in the task list.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     */
    @Override
    public void executeCli(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksListing(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
        return ui.getTasksListing(tasks);
    }
}
