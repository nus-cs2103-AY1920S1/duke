package puke.command;

import puke.storage.Storage;
import puke.task.TaskList;

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
