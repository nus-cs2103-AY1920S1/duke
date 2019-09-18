package taskchick.command;

import java.io.IOException;
import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;
import taskchick.task.Task;

/**
 * Command to add tasks to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates a AddCommand object with the new task assigned.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of the task.
     *
     * @param tasks Task list where the task will be added.
     * @param storage Storage to be updated with the new task.
     */
    public String execute(TaskList tasks, Storage storage) {
        UndoCommand.saveVersion(storage.getSavedListString(tasks));
        String response = tasks.addTask(task);
        assert(!response.isEmpty());
        try {
            storage.store(tasks);
        } catch (IOException e) {
            return "OOPS!!! " + e.getMessage();
        }
        return response;
    }
}
