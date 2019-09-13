package bari.command;

import bari.storage.Storage;
import bari.task.TaskList;

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
        tl.sort();
        respStrings.add("Tasks sorted.");
        respStrings.numTasksInList(tl);
        respStrings.writeToStorage(tl, storage);
    }
}
