import java.io.IOException;

/**
 * Represents a command that has to be carried out
 */
public abstract class Command {

    /**
     * Executes the Command
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public abstract String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether the command is to exit the program
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
