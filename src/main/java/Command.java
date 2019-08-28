public abstract class Command {
    protected boolean isExit;

    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    boolean isExit() {
        return isExit;
    }
}
