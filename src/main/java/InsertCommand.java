public class InsertCommand extends Command {
    private Task task;

    public InsertCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.addTaskMessage(tasks, task);
        storage.writeToFile(tasks);
    }
}
