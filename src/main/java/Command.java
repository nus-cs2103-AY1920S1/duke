import java.io.IOException;

public abstract class Command {
    protected boolean exit = false;

    public Command() {
    }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return exit;
    }

}
