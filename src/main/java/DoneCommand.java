public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public int getIndex() {
        return this.index - 1;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task doneTask = tasks.getTask(this.getIndex());
        tasks.markTaskDone(this.getIndex());
        ui.showDoneMessage(doneTask);
    }

    public boolean isExit() {
        return false;
    }
}
