package command;

import command.Command;
import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * A ClearAllTasksCommand object to deal with clearing tasks from the task list.
 */
public class ClearAllTasksCommand extends Command {

    /**
     * Constructs a ClearAllTasksCommand object to deal with clearing out all the tasks in an existing task list.
     */
    public ClearAllTasksCommand() {
        super();
    }

    /**
     * Executes the command to clear all existing tasks in the task list.
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @throws DukeException    If there is an error in clearing the file which stores the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.clearAll();
        ui.dukeEcho("All tasks have been cleared. You now have an empty task list!");
        tasks.clearAll();
    }
}
