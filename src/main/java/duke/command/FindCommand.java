package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.List;

/**
 * Finds a task by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find a task.
     * @param tasks Task list for task management.
     * @param ui UI for input and output.
     * @param storage To read and write tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert !keyword.isEmpty() : "Keyword should not empty";

        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);

        if (matchingTasks.size() == 0) {
            ui.printLine("No matching tasks in your list.");
            return;
        }

        ui.printLine("Here are the matching tasks in your list:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            String output = String.format("%d. %s", i + 1, matchingTasks.get(i));
            ui.printLine(output);
        }
    }
}
