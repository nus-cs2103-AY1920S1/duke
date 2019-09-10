package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * FindCommand class.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword Keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    public String[] execute(TaskList taskList) {
        ArrayList<Task> filteredList = new ArrayList<Task>();
        for (Task task : taskList) {
            if ((task.getDescription() + task.getTag()).contains(this.keyword)) {
                filteredList.add(task);
            }
        }
        return TaskList.printExternalList(filteredList, "Here are the matching tasks in your list:");
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
