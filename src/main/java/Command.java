/**
 * Represents a command.
 * A command can be of different types.
 */
public abstract class Command {

    /**
     * The exit status of the command that signals to the program.
     */
    public boolean isExit = false;

    /**
     * Executes the command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Executes the command in GUI.
     * @param tasks the task list
     * @param ui the Ui
     * @param storage the storage writer
     * @return
     */
    public abstract String executeForGUI(TaskList tasks, Storage storage) throws DukeException;
    public String string;
}
