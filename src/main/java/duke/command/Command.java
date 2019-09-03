package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Command class is an abstract class for all valid commands.
 */

public abstract class Command {

    /**
     * This method returns a boolean indicating whether the command is an exit command.
     * @return boolean true if exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * This is an abstract method that executes the correct actions based on the command.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
