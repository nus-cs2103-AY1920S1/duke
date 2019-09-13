package bari.command;

import bari.storage.Storage;
import bari.task.TaskList;

/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    private String str;

    /**
     * Creates a new DeleteCommand with the specified index.
     *
     * @param str The index of the task to delete, where the first task is 1.
     */
    public DeleteCommand(String str) {
        this.str = str;
    }

    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        int ind = respStrings.checkInteger(str, tl);
        respStrings.add(tl.delete(ind) + " was deleted.");
        respStrings.numTasksInList(tl);
        respStrings.writeToStorage(tl, storage);
    }
}
