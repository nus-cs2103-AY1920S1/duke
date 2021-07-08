package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST     = "Here are the tasks in your list:\n";
    private static final String MESSAGE_NO_TASKS = "You have no tasks in your list yet!";

    /**
     * Executes List command to list all tasks in given TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui.
     * @param storage Current Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder lines = new StringBuilder();
        if (tasks.isEmpty()) {
            lines.append(MESSAGE_NO_TASKS);
            ui.append(lines.toString());
            return;
        }
        lines.append(MESSAGE_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            lines.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        ui.append(lines.toString());
    }
}
