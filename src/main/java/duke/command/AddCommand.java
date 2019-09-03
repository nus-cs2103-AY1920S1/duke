package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.ArrayList;

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
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        tl.add(task);

        ArrayList<String> lines = new ArrayList<>();
        lines.add(task + " was added.");
        int n = tl.size();
        lines.add(String.format("%d task%s in your list.", n, n == 1 ? " is" : "s are"));
        
        try {
            storage.write(tl.export());
        } catch (IOException e) {
            lines.add("Error writing tasks to file!");
        }

        return String.join("\n", lines);
    }
}
