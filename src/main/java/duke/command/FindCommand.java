package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String search;

    private static final String MESSAGE_LIST = "Here are the matching tasks in your list:\n";
    private static final String MESSAGE_NO_TASKS = "You have no matching tasks in your list.";

    /**
     * Constructs a Find command. Searches for the search param in all task descriptions.
     *
     * @param search String to search in tasks.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Executes Find command to filter the given TaskList for those that contain the search term.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui.
     * @param storage Current Storage.
     * @throws DukeException Never.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(search)) {
                filteredTasks.add(task);
            }
        }

        StringBuilder lines = new StringBuilder();
        if (filteredTasks.isEmpty()) {
            lines.append(MESSAGE_NO_TASKS);
            ui.append(lines.toString());
            return;
        }
        lines.append(MESSAGE_LIST);
        for (int i = 0; i < filteredTasks.size(); i++) {
            lines.append(String.format("%d. %s\n", i + 1, filteredTasks.get(i).toString()));
        }
        ui.append(lines.toString());
    }
}
