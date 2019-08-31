package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import java.util.ArrayList;

/**
 * Class representing a command to list items in a task list.
 */
public class ListCommand extends Command {
    /**
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        if (tl.size() == 0) {
            return "You have no tasks.";
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add("You have these tasks:");
        for (int i = 1; i <= tl.size(); i++) {
            lines.add(i + ". " + tl.get(i));
        }

        return String.join("\n", lines);
    }
}
