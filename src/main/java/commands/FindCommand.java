package commands;

import exceptions.DukeException;
import tasks.Task;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Create a find command.
     *
     * @param keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Attempt to find the keyword among the current list of tasks,
     * by accessing the storage list.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     * @param storage to write changes to file
     * @return feedback from Duke
     * @throws DukeException if keyword is not found in task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);

        if (matchingTasks.isEmpty()) {
            throw new DukeException("No matching tasks found!");
        } else {
            return ui.printMatchingTasks(matchingTasks);
        }
    }
}
