import java.util.List;

public abstract class Command {
    protected boolean isExit;

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
