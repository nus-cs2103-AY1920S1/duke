import java.io.IOException;

public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    abstract boolean isExit();
}
