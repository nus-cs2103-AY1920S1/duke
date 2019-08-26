import java.io.FileNotFoundException;

public abstract class Command {
    public Command() {

    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException;

    public boolean isExit() {
        return false;
    }
}
