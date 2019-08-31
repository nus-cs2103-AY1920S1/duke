package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;

/**
 * Class representing a command to find items in the task list matching some keyword,
 * and to display those items.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword that is being searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        ArrayList<Task> filteredTasks = tl.filter(keyword);
        if (filteredTasks.size() == 0) {
            return "No tasks match.";
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add("These tasks match:");
        for (int i = 1; i <= filteredTasks.size(); i++) {
            lines.add(i + ". " + filteredTasks.get(i - 1));
        }

        return String.join("\n", lines);
    }
}
