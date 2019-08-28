import java.util.ArrayList;

public abstract class Command {
    protected String command;

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    abstract boolean isExit();
}