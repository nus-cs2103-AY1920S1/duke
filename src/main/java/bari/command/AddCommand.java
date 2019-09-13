package bari.command;

import bari.storage.Storage;
import bari.task.Task;
import bari.task.TaskList;

/**
 * Class representing a command to add a new task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command on the given parameters.
     *
     * @see Command#execute
     */
    public void execute(TaskList tl, ResponseStrings respStrings, Storage storage) {
        tl.add(task);
        respStrings.add(task + " was added.");
        respStrings.numTasksInList(tl);
        respStrings.writeToStorage(tl, storage);
    }
}
