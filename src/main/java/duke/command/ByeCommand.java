package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the actions to execute when the command 'bye' is
 * triggered.
 */


public class ByeCommand extends Command {

    /**
     * Prints goodbye message.
     * @param tasks List of Tasks
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the boolean true to signal termination of program.
     * @return returns the boolean true to mark the end of the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
