public abstract class Command {
    protected boolean isExit;
    protected String description;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList task, Ui ui, Storage storage)throws DukeException;
}
