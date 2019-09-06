public class Command {
    private boolean isExit = false;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // implementation varies for each subclass of Command
    }

    public boolean isExit() {
        return this.isExit;
    }
}
