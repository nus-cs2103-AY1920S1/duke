import java.io.IOException;

/**
 * Abstract command class to be inherited.
 */
public abstract class Command {
    /**
     * Abstract method execute which executes commands.
     */
    public abstract void execute(TaskList listOfTasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Abstract boolean method to know if it is an exit command.
     */
    public abstract boolean isExit();
}
