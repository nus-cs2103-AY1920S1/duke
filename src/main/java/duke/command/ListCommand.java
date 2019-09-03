package duke.command;

import duke.main.*;

/**
 * Represents the 'list' command
 */
public class ListCommand implements Command {
    public ListCommand() {
    }

    /**
     * Lists the current tasks in the task list
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.output("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            ui.output((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    public boolean isRunning() {
        return true;
    }
}
