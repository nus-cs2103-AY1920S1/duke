import java.io.IOException;

public abstract class Command {
    public abstract boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException;
}
