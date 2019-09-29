/**
 * Represents a user command. A <code>AddCommand</code> object corresponds to
 * with a valid task creation command e.g., <code>event test code /at 14/09/2019 0830</code>
 */

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.writeToFile(tasks);
        ui.addTaskMessage(tasks, task);
    }
}
