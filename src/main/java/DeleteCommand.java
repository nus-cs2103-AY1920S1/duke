public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        tasks.removeTask(index);
        ui.removeTaskMessage(tasks, task);
        storage.writeToFile(tasks);
    }
}
