package puke.command;

import puke.storage.Storage;
import puke.task.Task;
import puke.task.TaskList;
import java.io.IOException;

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
        
        try {
            storage.write(tl.export());
        } catch (IOException e) {
            respStrings.add("Error writing tasks to file!");
        }
    }
}
