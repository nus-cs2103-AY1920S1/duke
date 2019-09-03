package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Create a FindCommand. It search for tasks with given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find the tasks with the given keyword.
     * @param t TaskList to be appended.
     * @param ui UI to interact with user.
     * @param storage Storage to read and write files.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        String message = "";
        ArrayList<Task> foundTaskList = new ArrayList<>();
        for (Task task : t.tasks) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword)) {
                foundTaskList.add(task);
            }
        }
        message = ui.showFoundTask(foundTaskList);
        return message;
    }
}
