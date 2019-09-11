public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.setDone(true);
        ui.doneTaskMessage(task);
        storage.writeToFile(tasks);
    }
}
