package taskchick.command;

import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;

/**
 * Command to display all tasks in the list to user.
 */
public class ListCommand extends Command {

    /**
     * Executes the displaying of the task list.
     *
     * @param tasks Task list to display from.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = tasks.printTasks();
        assert (!response.isEmpty());
        return response;
    }
}
