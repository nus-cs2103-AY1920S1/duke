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
        throw new DukeException("I don't quite understand what you mean by that. Feel free to enter 'help'" +
                " to see all the commands you can enter.");
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I don't quite understand what you mean by that. Feel free to enter 'help'" +
                " to see all the commands you can enter.");
    }
}
