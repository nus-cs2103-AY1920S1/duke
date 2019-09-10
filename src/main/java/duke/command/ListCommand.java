package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents the actions to execute when the command 'list' is
 * triggered.
 */

public class ListCommand extends Command {

    /**
     * Prints all the tasks in the list.
     *
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     */

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
