package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    private final String s;

    /**
     * Creates a new DeleteCommand with the specified index.
     *
     * @param s The index of the task to delete, where the first task is 1.
     */
    public DeleteCommand(String s) {
        this.s = s;
    }

    /**
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        int ind;
        try {
            ind = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return "The task index must be an integer. I didn't do anything.";
        }
        int n = tl.size();
        if (ind < 1 || n < ind) {
            return String.format("You have %d task%s. The index must be between 1 and that number.",
                    n, n == 1 ? "" : "s");
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add(tl.delete(ind) + " was deleted.");
        n--;
        lines.add(String.format("%d task%s in your list.", n, n == 1 ? " is" : "s are"));

        try {
            storage.write(tl.export());
        } catch (IOException e) {
            lines.add("Error writing tasks to file!");
        }

        return String.join("\n", lines);
    }
}
