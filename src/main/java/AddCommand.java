/**
 * Represents the adding of tasks.
 */
public class AddCommand extends Command {

    private Task taskToAdd;

    /**
     * Creates an AddCommand object that stores the Task to be added to TaskList
     *
     * @param task Task that is to be added.
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the add command on a TaskList
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the task failed to be added.
     */
    public void execute(TaskList tasks) throws DukeException {
        tasks.addTask(this.taskToAdd);
    }
}
