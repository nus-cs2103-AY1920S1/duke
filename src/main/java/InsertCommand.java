public class InsertCommand extends Command {
    private Task task;

    public InsertCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.writeToFile(tasks);
        return ui.addTaskMessage(tasks, task);
    }
}
