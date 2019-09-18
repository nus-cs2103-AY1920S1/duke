import java.io.IOException;

public abstract class Command {

    public abstract boolean isExit();

    public abstract String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage)
            throws IOException, DukeException;
}
