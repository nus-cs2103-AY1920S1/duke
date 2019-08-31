import java.io.FileNotFoundException;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
