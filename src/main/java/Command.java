public abstract class Command {

    protected boolean isExit;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

}
