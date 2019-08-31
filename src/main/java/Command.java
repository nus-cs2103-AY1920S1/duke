public abstract class Command {

    protected boolean hasExit;

    public Command() {
        hasExit = false;
    }

    abstract void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;

    public boolean isExit() {
        return hasExit;
    }
}
