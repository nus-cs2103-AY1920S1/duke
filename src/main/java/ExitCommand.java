/**
 * Command to end the Duke session.
 */
public class ExitCommand extends Command {

    /**
     * Creates a ExitCommand object with the exit boolean set to true.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Executes the exit command.
     *
     * @param tasks Unused.
     * @param ui User interface that assists with printing.
     * @param storage Unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}
