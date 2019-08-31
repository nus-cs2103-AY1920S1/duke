public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
