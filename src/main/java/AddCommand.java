public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAddTaskMessage(this.task, tasks.taskListSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
