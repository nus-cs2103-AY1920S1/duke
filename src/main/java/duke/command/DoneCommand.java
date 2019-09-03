package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import java.io.IOException;
import java.util.ArrayList;

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
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        int ind;
        try {
            ind = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return "The task index must be an integer. I didn't do anything.";
        }
        int n = tl.size();
        if (ind < 1 || n < ind) {
            return String.format("You have %d task%s. The index must be between 1 and that number.",
                    n, n == 1 ? "" : "s");
        }
        tl.markDone(ind);

        ArrayList<String> lines = new ArrayList<>();
        lines.add(tl.get(ind) + " is done.");

        try {
            storage.write(tl.export());
        } catch (IOException e) {
            lines.add("Error writing tasks to file!");
        }

        return String.join("\n", lines);
    }
}
