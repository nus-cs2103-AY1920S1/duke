public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public int getIndex() {
        return this.index - 1;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task deletedTask = tasks.getTask(this.getIndex());
        tasks.deleteTask(this.getIndex());
        ui.showDeleteMessage(deletedTask, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
