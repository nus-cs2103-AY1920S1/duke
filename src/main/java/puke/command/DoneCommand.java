package puke.command;

import puke.storage.Storage;
import puke.task.TaskList;

/**
 * Class representing a command to mark an item in the task list as done.
 */
public class DoneCommand extends Command {
    private String str;

    /**
     * Creates a new DoneCommand with the specified index.
     *
     * @param str The index of the task to mark as done, where the first task is 1.
     */
    public DoneCommand(String str) {
        this.str = str;
    }

    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        int ind = respStrings.checkInteger(str, tl);
        tl.markDone(ind);
        respStrings.add(tl.get(ind) + " is done.");
        respStrings.writeToStorage(tl, storage);
    }
}
