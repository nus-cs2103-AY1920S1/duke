/**
 * A Command to exit the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the Duke program.
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
