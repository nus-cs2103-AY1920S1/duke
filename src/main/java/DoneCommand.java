public class DoneCommand extends Command {
    private int idxToMarkAsDone;

    public DoneCommand(int idxToMarkAsDone) {
        this.idxToMarkAsDone = idxToMarkAsDone;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToMarkAsDone = tasks.getTask(this.idxToMarkAsDone);
        taskToMarkAsDone.markAsDone();
        ui.printDone(taskToMarkAsDone);
    }
}
