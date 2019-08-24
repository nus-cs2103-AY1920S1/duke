import task.Task;

public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void executeAddTask(TaskList tasks, Ui ui, Task task) {
        tasks.addTask(task);
        ui.showTaskAdded(task);
        ui.showNumTasks(tasks.getNumTasks());
    }
}
