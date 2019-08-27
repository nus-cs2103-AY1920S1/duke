public class DoneCommand extends Command {
    private int position;

    public DoneCommand(int position) {
        super();
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(this.position);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
