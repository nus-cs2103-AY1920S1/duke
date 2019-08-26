public abstract class Command {
    public boolean canEnd() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
