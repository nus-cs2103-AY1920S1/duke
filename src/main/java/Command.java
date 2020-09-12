/**
 * Encapsulates a user command abstractly.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract String executeForGui(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
