import java.io.IOException;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage, String command) throws IOException, DukeException;

}
