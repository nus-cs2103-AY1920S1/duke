/**
 * Encapsulates a user command of unrecognised input.
 */
public class UnrecognisedInputCommand extends Command {

    /**
     * Overridden method. Executes the unrecognised input command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     * @throws DukeException exception specific to Duke application
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
