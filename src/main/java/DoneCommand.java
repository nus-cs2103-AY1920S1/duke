public class DoneCommand extends Command {

    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskChanged = tasks.markDone(taskNum);
        ui.showChangedTask(taskChanged);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}