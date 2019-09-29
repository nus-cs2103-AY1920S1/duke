/**
 * Represents the add command which is activated when there is a valid task.
 * An <code>AddCommand</code> object adds valid to task to the taskList.
 * e.g., <code>/*event test </code>
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
