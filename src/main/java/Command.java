import java.util.List;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    abstract public void execute(TaskList taskList, Ui ui, Storage storage);
}
