package puke.command;

import puke.storage.Storage;
import puke.task.TaskList;
import java.io.IOException;

/**
 * Class representing a command to sort the task list in-place.
 */
public class SortCommand extends Command {
    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        respStrings.sortTasks(tl);
        respStrings.numTasksInList(tl);
        respStrings.writeToStorage(tl, storage);
    }
}
