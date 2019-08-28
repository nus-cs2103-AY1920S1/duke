import java.io.IOException;

/**
 * A command made from user input, that will determine the action to be taken on the task list.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public abstract boolean isExit();
}
