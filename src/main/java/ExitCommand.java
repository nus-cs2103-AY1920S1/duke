/**
 * ExitCommands represent a user command to exit the program
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    /**
     * Shows an exit message
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.showExit();
    }
}
