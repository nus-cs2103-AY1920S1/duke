package duke.command;

import java.io.IOException;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.task.Task;

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
        String response = tasks.addTask(task);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            return "    OOPS!!! " + e.getMessage();
        }
        return response;
    }
}
