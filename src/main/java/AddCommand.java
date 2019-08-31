import java.io.IOException;

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
     * @param ui User interface that assists with printing.
     * @param storage Storage to be updated with the new task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    OOPS!!! " + e.getMessage());
        }
    }
}
