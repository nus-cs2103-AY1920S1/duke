package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    private static final String MESSAGE_LIST = "Here are the matching tasks in your list:\n";
    private static final String MESSAGE_NO_TASKS = "You have no matching tasks in your list.";

    /**
     * Constructs a Find command. Searches for the search param in all task descriptions.
     *
     * @param keyword String to search in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes Find command to filter the given TaskList for those that contain the search term.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui.
     * @param storage Current Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }

        StringBuilder searchResults = new StringBuilder();
        if (filteredTasks.isEmpty()) {
            searchResults.append(MESSAGE_NO_TASKS);
            ui.append(searchResults.toString());
            return;
        }
        searchResults.append(MESSAGE_LIST);
        for (int i = 0; i < filteredTasks.size(); i++) {
            searchResults.append(String.format("%d. %s\n", i + 1, filteredTasks.get(i).toString()));
        }
        ui.append(searchResults.toString());
    }
}
