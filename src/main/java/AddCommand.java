public class AddCommand extends Command {

    protected Task addedTask;

    public AddCommand(String fullCommand, Task addedTask) {
        this.fullCommand = fullCommand;
        this.addedTask = addedTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(addedTask);
        ui.showTaskAdded(addedTask, tasks.getNumberOfTasks());
    }

    public boolean isExit() {
        return false;
    }
}
