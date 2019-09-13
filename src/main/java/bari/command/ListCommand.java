package bari.command;

import bari.storage.Storage;
import bari.task.TaskList;

/**
 * Class representing a command to list items in a task list.
 */
public class ListCommand extends Command {
    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        respStrings.listTasks(tl);
    }
}
