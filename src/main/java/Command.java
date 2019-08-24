import java.util.List;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
