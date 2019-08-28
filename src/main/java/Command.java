public abstract class Command {
    protected boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, UI ui);
}