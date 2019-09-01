public abstract class Command {
    protected String fullCommand;

    public abstract Command clone(String fullCommand);
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}